# CLAUDE.md

このファイルは、このリポジトリで作業する Claude Code (claude.ai/code) に向けたガイドを提供するものです。

# 作業ルール

- この環境では .git が読み取り専用。commit、push、ブランチ操作はしない。人間が Android Studio で行う。
- 作業が終わったら、変更したファイルの一覧を最後に示す。
- build.gradle(.kts)、settings.gradle(.kts)、gradle.properties、gradle/ 以下、gradlew を変更したときは、そのことと理由を必ず伝える。
- .env や鍵ファイルを作らない。
- ドキュメント（CLAUDE.md、README、設計メモなど）を新規作成・追記する際は、原則として日本語で書く。

## プロジェクト概要

FragmentBread（rootProject 名は "Fragment Bread"）は、`RecyclerView` と AndroidX の `recyclerview-selection` を使ったマスターリストを、Fragment ベースで実装した小さな単一モジュールの Android アプリ。`applicationId`／`namespace` は `com.example.fragmentbread`。アプリコードは基本 Java で、データクラス 1 つだけ Kotlin。

## ビルド・テストコマンド

この環境は `.git` が読み取り専用で、開発ツール類はすべて Gradle 経由なので wrapper を使う。

- デバッグ APK のビルド: `./gradlew assembleDebug`
- 単体テスト実行（JVM、`app/src/test`）: `./gradlew testDebugUnitTest`
- 単体テストを 1 クラスだけ実行: `./gradlew testDebugUnitTest --tests "com.example.fragmentbread.ExampleUnitTest"`
- インストゥルメンテーションテスト実行（`app/src/androidTest`、デバイス／エミュレータが必要）: `./gradlew connectedDebugAndroidTest`
- Lint: `./gradlew lint`

依存バージョンは `gradle/libs.versions.toml`（version catalog）に集約されており、`app/build.gradle.kts` は `libs.*` 経由で参照している。

## アーキテクチャ

- **MainActivity**（`MainActivity.java`） — 単一 Activity。edge-to-edge 有効。view binding（`buildFeatures.viewBinding = true`）経由で `activity_main.xml` をホストする。Fragment 自体はこの Activity のレイアウト XML 内に静的に宣言されており、実行時に差し替えるものではない。
- **MainFragment**（`fragments/MainFragment.java`） — ハードコードされたメモリ上の `List<StoreState>` データセットを保持し、`RecyclerView` をセットアップし、複数選択用に AndroidX の `SelectionTracker<Long>` を構築する。
  - `StableIdKeyProvider` と、アダプタ側の `setHasStableIds(true)` により、安定した item ID が必須（`StoreState.storeId` が `getItemId` 経由で RecyclerView の item ID を兼ねている）。
  - 選択状態の保存／復元は、Fragment 自身の Bundle ではなく tracker の `onSaveInstanceState`/`onRestoreInstanceState` を通して行う。
  - tracker は構築後に `adapter.setTracker(...)` でアダプタへ注入されるため、Fragment とアダプタは同一の tracker インスタンスを共有する。
- **StoreListAdapter**（`adapters/StoreListAdapter.java`） — `StoreState` を `view_store_list_item.xml` にバインドし、`ConstraintLayout.setActivated(...)` の切り替えで選択状態を反映する。これが `drawable/store_list_item_background.xml` の `activated` ステートに連動する。
- **adapters/selection/** — AndroidX selection ライブラリ用の 3 つのフック：`StoreListItemDetailsLookup`（タッチイベントを item details にマッピング）、`StoreListOnItemActivatedListener`、`StoreListSelectionObserver`。選択動作を変更する際は、これら 3 つと `StoreListAdapter.ViewHolder.getItemDetails()` を整合させる必要がある。
- **StoreState**（`viewmodels/StoreState.kt`） — 単純な Kotlin データクラス（`storeId: Long`、`storeName: String`）。パッケージ名に反して、このコードベースに `ViewModel` クラスは存在しない。

ネットワーク層・永続化層・DI フレームワークは存在しない。データセットは `MainFragment.onCreate` で生成され、メモリ上にのみ存在する。
