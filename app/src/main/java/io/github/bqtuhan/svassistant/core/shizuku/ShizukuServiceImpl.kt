package io.github.bqtuhan.svassistant.core.shizuku

import android.content.Context
import android.os.IBinder
import android.os.ParcelFileDescriptor
import android.util.Log
import java.io.File
import java.io.FileNotFoundException

/**
 * Runs in the Shizuku server process with ADB privileges.
 * Safely opens a file descriptor to the raw XML save file from /Android/data/
 * and passes it back across the Binder without reading the contents into RAM.
 */
class ShizukuServiceImpl(context: Context) : IShizukuService.Stub() {
    
    companion object {
        private const val TAG = "ShizukuServiceImpl"
    }

    override fun getSaveFileDescriptor(path: String): ParcelFileDescriptor {
        val file = File(path)
        if (!file.exists() || !file.canRead()) {
            Log.e(TAG, "File not found or unreadable: $path")
            throw FileNotFoundException("File not found or unreadable: $path")
        }
        return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
    }

    override fun destroy(): Boolean {
        // Clean up resources if necessary
        return true
    }
}