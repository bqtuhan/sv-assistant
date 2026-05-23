package io.github.bqtuhan.svassistant;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import io.github.bqtuhan.svassistant.core.ai.AiRepository;
import io.github.bqtuhan.svassistant.core.ai.RagContextGenerator;
import io.github.bqtuhan.svassistant.core.parser.SaveParser;
import io.github.bqtuhan.svassistant.core.security.SecurityManager;
import io.github.bqtuhan.svassistant.core.shizuku.ShizukuManager;
import io.github.bqtuhan.svassistant.core.storage.AssetRepository;
import io.github.bqtuhan.svassistant.core.storage.StorageRepository;
import io.github.bqtuhan.svassistant.di.AppModule_ProvideIoDispatcherFactory;
import io.github.bqtuhan.svassistant.ui.viewmodel.AiViewModel;
import io.github.bqtuhan.svassistant.ui.viewmodel.AiViewModel_HiltModules;
import io.github.bqtuhan.svassistant.ui.viewmodel.DashboardViewModel;
import io.github.bqtuhan.svassistant.ui.viewmodel.DashboardViewModel_HiltModules;
import io.github.bqtuhan.svassistant.ui.viewmodel.JournalViewModel;
import io.github.bqtuhan.svassistant.ui.viewmodel.JournalViewModel_HiltModules;
import io.github.bqtuhan.svassistant.ui.viewmodel.KnowledgeViewModel;
import io.github.bqtuhan.svassistant.ui.viewmodel.KnowledgeViewModel_HiltModules;
import io.github.bqtuhan.svassistant.ui.viewmodel.SettingsViewModel;
import io.github.bqtuhan.svassistant.ui.viewmodel.SettingsViewModel_HiltModules;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineDispatcher;

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
public final class DaggerSVApplication_HiltComponents_SingletonC {
  private DaggerSVApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public SVApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements SVApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public SVApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements SVApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public SVApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements SVApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public SVApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements SVApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public SVApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements SVApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public SVApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements SVApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public SVApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements SVApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public SVApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends SVApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends SVApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends SVApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends SVApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(5).put(LazyClassKeyProvider.io_github_bqtuhan_svassistant_ui_viewmodel_AiViewModel, AiViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.io_github_bqtuhan_svassistant_ui_viewmodel_DashboardViewModel, DashboardViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.io_github_bqtuhan_svassistant_ui_viewmodel_JournalViewModel, JournalViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.io_github_bqtuhan_svassistant_ui_viewmodel_KnowledgeViewModel, KnowledgeViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.io_github_bqtuhan_svassistant_ui_viewmodel_SettingsViewModel, SettingsViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String io_github_bqtuhan_svassistant_ui_viewmodel_SettingsViewModel = "io.github.bqtuhan.svassistant.ui.viewmodel.SettingsViewModel";

      static String io_github_bqtuhan_svassistant_ui_viewmodel_JournalViewModel = "io.github.bqtuhan.svassistant.ui.viewmodel.JournalViewModel";

      static String io_github_bqtuhan_svassistant_ui_viewmodel_AiViewModel = "io.github.bqtuhan.svassistant.ui.viewmodel.AiViewModel";

      static String io_github_bqtuhan_svassistant_ui_viewmodel_KnowledgeViewModel = "io.github.bqtuhan.svassistant.ui.viewmodel.KnowledgeViewModel";

      static String io_github_bqtuhan_svassistant_ui_viewmodel_DashboardViewModel = "io.github.bqtuhan.svassistant.ui.viewmodel.DashboardViewModel";

      @KeepFieldType
      SettingsViewModel io_github_bqtuhan_svassistant_ui_viewmodel_SettingsViewModel2;

      @KeepFieldType
      JournalViewModel io_github_bqtuhan_svassistant_ui_viewmodel_JournalViewModel2;

      @KeepFieldType
      AiViewModel io_github_bqtuhan_svassistant_ui_viewmodel_AiViewModel2;

      @KeepFieldType
      KnowledgeViewModel io_github_bqtuhan_svassistant_ui_viewmodel_KnowledgeViewModel2;

      @KeepFieldType
      DashboardViewModel io_github_bqtuhan_svassistant_ui_viewmodel_DashboardViewModel2;
    }
  }

  private static final class ViewModelCImpl extends SVApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AiViewModel> aiViewModelProvider;

    private Provider<DashboardViewModel> dashboardViewModelProvider;

    private Provider<JournalViewModel> journalViewModelProvider;

    private Provider<KnowledgeViewModel> knowledgeViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.aiViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.journalViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.knowledgeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(5).put(LazyClassKeyProvider.io_github_bqtuhan_svassistant_ui_viewmodel_AiViewModel, ((Provider) aiViewModelProvider)).put(LazyClassKeyProvider.io_github_bqtuhan_svassistant_ui_viewmodel_DashboardViewModel, ((Provider) dashboardViewModelProvider)).put(LazyClassKeyProvider.io_github_bqtuhan_svassistant_ui_viewmodel_JournalViewModel, ((Provider) journalViewModelProvider)).put(LazyClassKeyProvider.io_github_bqtuhan_svassistant_ui_viewmodel_KnowledgeViewModel, ((Provider) knowledgeViewModelProvider)).put(LazyClassKeyProvider.io_github_bqtuhan_svassistant_ui_viewmodel_SettingsViewModel, ((Provider) settingsViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String io_github_bqtuhan_svassistant_ui_viewmodel_JournalViewModel = "io.github.bqtuhan.svassistant.ui.viewmodel.JournalViewModel";

      static String io_github_bqtuhan_svassistant_ui_viewmodel_KnowledgeViewModel = "io.github.bqtuhan.svassistant.ui.viewmodel.KnowledgeViewModel";

      static String io_github_bqtuhan_svassistant_ui_viewmodel_AiViewModel = "io.github.bqtuhan.svassistant.ui.viewmodel.AiViewModel";

      static String io_github_bqtuhan_svassistant_ui_viewmodel_SettingsViewModel = "io.github.bqtuhan.svassistant.ui.viewmodel.SettingsViewModel";

      static String io_github_bqtuhan_svassistant_ui_viewmodel_DashboardViewModel = "io.github.bqtuhan.svassistant.ui.viewmodel.DashboardViewModel";

      @KeepFieldType
      JournalViewModel io_github_bqtuhan_svassistant_ui_viewmodel_JournalViewModel2;

      @KeepFieldType
      KnowledgeViewModel io_github_bqtuhan_svassistant_ui_viewmodel_KnowledgeViewModel2;

      @KeepFieldType
      AiViewModel io_github_bqtuhan_svassistant_ui_viewmodel_AiViewModel2;

      @KeepFieldType
      SettingsViewModel io_github_bqtuhan_svassistant_ui_viewmodel_SettingsViewModel2;

      @KeepFieldType
      DashboardViewModel io_github_bqtuhan_svassistant_ui_viewmodel_DashboardViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // io.github.bqtuhan.svassistant.ui.viewmodel.AiViewModel 
          return (T) new AiViewModel(singletonCImpl.aiRepositoryProvider.get(), singletonCImpl.ragContextGeneratorProvider.get(), singletonCImpl.storageRepositoryProvider.get());

          case 1: // io.github.bqtuhan.svassistant.ui.viewmodel.DashboardViewModel 
          return (T) new DashboardViewModel(singletonCImpl.storageRepositoryProvider.get());

          case 2: // io.github.bqtuhan.svassistant.ui.viewmodel.JournalViewModel 
          return (T) new JournalViewModel(singletonCImpl.storageRepositoryProvider.get());

          case 3: // io.github.bqtuhan.svassistant.ui.viewmodel.KnowledgeViewModel 
          return (T) new KnowledgeViewModel(singletonCImpl.assetRepositoryProvider.get());

          case 4: // io.github.bqtuhan.svassistant.ui.viewmodel.SettingsViewModel 
          return (T) new SettingsViewModel(singletonCImpl.storageRepositoryProvider.get(), singletonCImpl.securityManagerProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends SVApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends SVApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends SVApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<SecurityManager> securityManagerProvider;

    private Provider<CoroutineDispatcher> provideIoDispatcherProvider;

    private Provider<AiRepository> aiRepositoryProvider;

    private Provider<AssetRepository> assetRepositoryProvider;

    private Provider<RagContextGenerator> ragContextGeneratorProvider;

    private Provider<ShizukuManager> shizukuManagerProvider;

    private Provider<SaveParser> saveParserProvider;

    private Provider<StorageRepository> storageRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.securityManagerProvider = DoubleCheck.provider(new SwitchingProvider<SecurityManager>(singletonCImpl, 1));
      this.provideIoDispatcherProvider = DoubleCheck.provider(new SwitchingProvider<CoroutineDispatcher>(singletonCImpl, 2));
      this.aiRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AiRepository>(singletonCImpl, 0));
      this.assetRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AssetRepository>(singletonCImpl, 4));
      this.ragContextGeneratorProvider = DoubleCheck.provider(new SwitchingProvider<RagContextGenerator>(singletonCImpl, 3));
      this.shizukuManagerProvider = DoubleCheck.provider(new SwitchingProvider<ShizukuManager>(singletonCImpl, 6));
      this.saveParserProvider = DoubleCheck.provider(new SwitchingProvider<SaveParser>(singletonCImpl, 7));
      this.storageRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<StorageRepository>(singletonCImpl, 5));
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @Override
    public void injectSVApplication(SVApplication arg0) {
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // io.github.bqtuhan.svassistant.core.ai.AiRepository 
          return (T) new AiRepository(singletonCImpl.securityManagerProvider.get(), singletonCImpl.provideIoDispatcherProvider.get());

          case 1: // io.github.bqtuhan.svassistant.core.security.SecurityManager 
          return (T) new SecurityManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // @io.github.bqtuhan.svassistant.di.IoDispatcher kotlinx.coroutines.CoroutineDispatcher 
          return (T) AppModule_ProvideIoDispatcherFactory.provideIoDispatcher();

          case 3: // io.github.bqtuhan.svassistant.core.ai.RagContextGenerator 
          return (T) new RagContextGenerator(singletonCImpl.assetRepositoryProvider.get());

          case 4: // io.github.bqtuhan.svassistant.core.storage.AssetRepository 
          return (T) new AssetRepository(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideIoDispatcherProvider.get());

          case 5: // io.github.bqtuhan.svassistant.core.storage.StorageRepository 
          return (T) new StorageRepository(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.shizukuManagerProvider.get(), singletonCImpl.saveParserProvider.get(), singletonCImpl.provideIoDispatcherProvider.get());

          case 6: // io.github.bqtuhan.svassistant.core.shizuku.ShizukuManager 
          return (T) new ShizukuManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideIoDispatcherProvider.get());

          case 7: // io.github.bqtuhan.svassistant.core.parser.SaveParser 
          return (T) new SaveParser(singletonCImpl.provideIoDispatcherProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
