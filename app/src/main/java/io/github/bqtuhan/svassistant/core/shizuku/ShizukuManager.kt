package io.github.bqtuhan.svassistant.core.shizuku

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.IBinder
import android.os.ParcelFileDescriptor
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.bqtuhan.svassistant.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import rikka.shizuku.Shizuku
import rikka.shizuku.Shizuku.UserServiceArgs
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class ShizukuManager @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    private var service: IShizukuService? = null
    
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            if (binder != null && binder.pingBinder()) {
                service = IShizukuService.Stub.asInterface(binder)
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            service = null
        }
    }

    fun checkPermission(): Boolean {
        if (Shizuku.isPreV11()) return false
        return Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(requestCode: Int) {
        if (Shizuku.isPreV11()) return
        Shizuku.requestPermission(requestCode)
    }

    suspend fun bindService(): Boolean = withContext(ioDispatcher) {        if (service != null) return@withContext true
        
        suspendCancellableCoroutine { cont ->
            val listener = object : Shizuku.OnBinderReceivedListener {
                override fun onBinderReceived() {
                    Shizuku.removeBinderReceivedListener(this)
                    try {
                        val args = UserServiceArgs(
                            ComponentName(context.packageName, ShizukuServiceImpl::class.java.name)
                        ).version(1).processNameSuffix("svassistant").debuggable(true)
                        
                        Shizuku.bindUserService(args, serviceConnection)
                        // Give it a moment to connect
                        Thread.sleep(500) 
                        cont.resume(service != null)
                    } catch (e: Exception) {
                        cont.resume(false)
                    }
                }
            }
            
            if (Shizuku.pingBinder()) {
                listener.onBinderReceived()
            } else {
                Shizuku.addBinderReceivedListenerSticky(listener)
            }
            
            cont.invokeOnCancellation {
                Shizuku.removeBinderReceivedListener(listener)
            }
        }
    }

    suspend fun getSaveInputStream(path: String): InputStream? = withContext(ioDispatcher) {
        try {
            val pfd = service?.getSaveFileDescriptor(path)
            if (pfd != null) {
                // AutoCloseInputStream ensures the ParcelFileDescriptor is closed when the stream is closed
                ParcelFileDescriptor.AutoCloseInputStream(pfd)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    fun unbindService() {
        try {
            Shizuku.unbindUserService(                UserServiceArgs(ComponentName(context.packageName, ShizukuServiceImpl::class.java.name)),
                serviceConnection,
                true
            )
        } catch (e: Exception) {
            // Ignore
        }
        service = null
    }
}