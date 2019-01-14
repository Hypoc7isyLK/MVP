package com.bwie.likuo.app;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * date:2019/1/7
 * author:李阔(淡意衬优柔)
 * function:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DiskCacheConfig config = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("image")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();
        ImagePipelineConfig build = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(config)
                .build();

        Fresco.initialize(this,build);

    }
}
