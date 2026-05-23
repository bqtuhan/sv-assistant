package io.github.bqtuhan.svassistant.core.ai;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.github.bqtuhan.svassistant.core.security.SecurityManager;
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
public final class AiRepository_Factory implements Factory<AiRepository> {
  private final Provider<SecurityManager> securityManagerProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  public AiRepository_Factory(Provider<SecurityManager> securityManagerProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.securityManagerProvider = securityManagerProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public AiRepository get() {
    return newInstance(securityManagerProvider.get(), ioDispatcherProvider.get());
  }

  public static AiRepository_Factory create(Provider<SecurityManager> securityManagerProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new AiRepository_Factory(securityManagerProvider, ioDispatcherProvider);
  }

  public static AiRepository newInstance(SecurityManager securityManager,
      CoroutineDispatcher ioDispatcher) {
    return new AiRepository(securityManager, ioDispatcher);
  }
}
