package io.github.bqtuhan.svassistant.ui.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.github.bqtuhan.svassistant.core.storage.AssetRepository;
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
public final class KnowledgeViewModel_Factory implements Factory<KnowledgeViewModel> {
  private final Provider<AssetRepository> assetRepositoryProvider;

  public KnowledgeViewModel_Factory(Provider<AssetRepository> assetRepositoryProvider) {
    this.assetRepositoryProvider = assetRepositoryProvider;
  }

  @Override
  public KnowledgeViewModel get() {
    return newInstance(assetRepositoryProvider.get());
  }

  public static KnowledgeViewModel_Factory create(
      Provider<AssetRepository> assetRepositoryProvider) {
    return new KnowledgeViewModel_Factory(assetRepositoryProvider);
  }

  public static KnowledgeViewModel newInstance(AssetRepository assetRepository) {
    return new KnowledgeViewModel(assetRepository);
  }
}
