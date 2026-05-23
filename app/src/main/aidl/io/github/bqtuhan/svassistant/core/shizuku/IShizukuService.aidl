package io.github.bqtuhan.svassistant.core.shizuku;

import android.os.ParcelFileDescriptor;

interface IShizukuService {
    ParcelFileDescriptor getSaveFileDescriptor(String path);
    boolean destroy();
}