package io.github.bqtuhan.svassistant.ui.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<StorageRepository> storageRepositoryProvider;

  public DashboardViewModel_Factory(Provider<StorageRepository> storageRepositoryProvider) {
    this.storageRepositoryProvider = storageRepositoryProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(storageRepositoryProvider.get());
  }

  public static DashboardViewModel_Factory create(
      Provider<StorageRepository> storageRepositoryProvider) {
    return new DashboardViewModel_Factory(storageRepositoryProvider);
  }

  public static DashboardViewModel newInstance(StorageRepository storageRepository) {
    return new DashboardViewModel(storageRepository);
  }
}
