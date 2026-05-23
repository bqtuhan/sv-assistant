package io.github.bqtuhan.svassistant.core.storage;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class SafManager_Factory implements Factory<SafManager> {
  private final Provider<Context> contextProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public SafManager_Factory(Provider<Context> contextProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.contextProvider = contextProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public SafManager get() {
    return newInstance(contextProvider.get(), ioDispatcherProvider.get());
  }

  public static SafManager_Factory create(Provider<Context> contextProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new SafManager_Factory(contextProvider, ioDispatcherProvider);
  }

  public static SafManager newInstance(Context context, CoroutineDispatcher ioDispatcher) {
    return new SafManager(context, ioDispatcher);
  }
}
