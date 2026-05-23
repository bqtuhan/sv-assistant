package io.github.bqtuhan.svassistant.core.storage;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.github.bqtuhan.svassistant.core.parser.SaveParser;
import io.github.bqtuhan.svassistant.core.shizuku.ShizukuManager;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata({
    "dagger.hilt.android.qualifiers.ApplicationContext",
    "io.github.bqtuhan.svassistant.di.IoDispatcher"
})
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class StorageRepository_Factory implements Factory<StorageRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<ShizukuManager> shizukuManagerProvider;

  private final Provider<SaveParser> saveParserProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public StorageRepository_Factory(Provider<Context> contextProvider,
      Provider<ShizukuManager> shizukuManagerProvider, Provider<SaveParser> saveParserProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.contextProvider = contextProvider;
    this.shizukuManagerProvider = shizukuManagerProvider;
    this.saveParserProvider = saveParserProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public StorageRepository get() {
    return newInstance(contextProvider.get(), shizukuManagerProvider.get(), saveParserProvider.get(), ioDispatcherProvider.get());
  }

  public static StorageRepository_Factory create(Provider<Context> contextProvider,
      Provider<ShizukuManager> shizukuManagerProvider, Provider<SaveParser> saveParserProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new StorageRepository_Factory(contextProvider, shizukuManagerProvider, saveParserProvider, ioDispatcherProvider);
  }

  public static StorageRepository newInstance(Context context, ShizukuManager shizukuManager,
      SaveParser saveParser, CoroutineDispatcher ioDispatcher) {
    return new StorageRepository(context, shizukuManager, saveParser, ioDispatcher);
  }
}
