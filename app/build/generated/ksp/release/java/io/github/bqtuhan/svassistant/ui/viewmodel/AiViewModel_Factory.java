package io.github.bqtuhan.svassistant.ui.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.github.bqtuhan.svassistant.core.ai.AiRepository;
import io.github.bqtuhan.svassistant.core.ai.RagContextGenerator;
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
public final class AiViewModel_Factory implements Factory<AiViewModel> {
  private final Provider<AiRepository> aiRepositoryProvider;

  private final Provider<RagContextGenerator> ragContextGeneratorProvider;

  private final Provider<StorageRepository> storageRepositoryProvider;

  public AiViewModel_Factory(Provider<AiRepository> aiRepositoryProvider,
      Provider<RagContextGenerator> ragContextGeneratorProvider,
      Provider<StorageRepository> storageRepositoryProvider) {
    this.aiRepositoryProvider = aiRepositoryProvider;
    this.ragContextGeneratorProvider = ragContextGeneratorProvider;
    this.storageRepositoryProvider = storageRepositoryProvider;
  }

  @Override
  public AiViewModel get() {
    return newInstance(aiRepositoryProvider.get(), ragContextGeneratorProvider.get(), storageRepositoryProvider.get());
  }

  public static AiViewModel_Factory create(Provider<AiRepository> aiRepositoryProvider,
      Provider<RagContextGenerator> ragContextGeneratorProvider,
      Provider<StorageRepository> storageRepositoryProvider) {
    return new AiViewModel_Factory(aiRepositoryProvider, ragContextGeneratorProvider, storageRepositoryProvider);
  }

  public static AiViewModel newInstance(AiRepository aiRepository,
      RagContextGenerator ragContextGenerator, StorageRepository storageRepository) {
    return new AiViewModel(aiRepository, ragContextGenerator, storageRepository);
  }
}
