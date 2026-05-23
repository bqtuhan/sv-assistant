package io.github.bqtuhan.svassistant.core.ai;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.github.bqtuhan.svassistant.core.storage.AssetRepository;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RagContextGenerator_Factory implements Factory<RagContextGenerator> {
  private final Provider<AssetRepository> assetRepositoryProvider;

  public RagContextGenerator_Factory(Provider<AssetRepository> assetRepositoryProvider) {
    this.assetRepositoryProvider = assetRepositoryProvider;
  }

  @Override
  public RagContextGenerator get() {
    return newInstance(assetRepositoryProvider.get());
  }

  public static RagContextGenerator_Factory create(
      Provider<AssetRepository> assetRepositoryProvider) {
    return new RagContextGenerator_Factory(assetRepositoryProvider);
  }

  public static RagContextGenerator newInstance(AssetRepository assetRepository) {
    return new RagContextGenerator(assetRepository);
  }
}
