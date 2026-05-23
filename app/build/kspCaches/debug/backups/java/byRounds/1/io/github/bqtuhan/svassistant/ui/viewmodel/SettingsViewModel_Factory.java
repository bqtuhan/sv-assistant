package io.github.bqtuhan.svassistant.ui.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.github.bqtuhan.svassistant.core.security.SecurityManager;
import io.github.bqtuhan.svassistant.core.storage.StorageRepository;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<StorageRepository> storageRepositoryProvider;

  private final Provider<SecurityManager> securityManagerProvider;

  public SettingsViewModel_Factory(Provider<StorageRepository> storageRepositoryProvider,
      Provider<SecurityManager> securityManagerProvider) {
    this.storageRepositoryProvider = storageRepositoryProvider;
    this.securityManagerProvider = securityManagerProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(storageRepositoryProvider.get(), securityManagerProvider.get());
  }

  public static SettingsViewModel_Factory create(
      Provider<StorageRepository> storageRepositoryProvider,
      Provider<SecurityManager> securityManagerProvider) {
    return new SettingsViewModel_Factory(storageRepositoryProvider, securityManagerProvider);
  }

  public static SettingsViewModel newInstance(StorageRepository storageRepository,
      SecurityManager securityManager) {
    return new SettingsViewModel(storageRepository, securityManager);
  }
}
