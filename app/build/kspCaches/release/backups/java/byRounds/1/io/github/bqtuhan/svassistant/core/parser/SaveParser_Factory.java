package io.github.bqtuhan.svassistant.core.parser;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("io.github.bqtuhan.svassistant.di.IoDispatcher")
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
public final class SaveParser_Factory implements Factory<SaveParser> {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public SaveParser_Factory(Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public SaveParser get() {
    return newInstance(ioDispatcherProvider.get());
  }

  public static SaveParser_Factory create(Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new SaveParser_Factory(ioDispatcherProvider);
  }

  public static SaveParser newInstance(CoroutineDispatcher ioDispatcher) {
    return new SaveParser(ioDispatcher);
  }
}
