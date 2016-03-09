### TS_MultiConnect
TeratermStation用の同サーバ複数接続プラグインです。  

##### セットアップ
- multiconnect-X.X.X.jar をGithub/releasesから取得します。
- 定義基点ディレクトリ直下のpluginsディレクトリ下にjarを配置します。
- TeratermStationを起動します。

##### 使い方
- サーバを右クリックすると「同サーバ複数接続」というメニューが出てきます。  
- 実行すると接続数を入力するダイアログが表示されるので、任意の数を指定します。  
指定した数のターミナルが順次開いていき、サーバログインが行われます。  
この接続数の上限値はTeratermStationの`基本設定内->拡張機能設定->マルチ接続設定`で設定することができます。  
これが設定されていない場合のデフォルトの上限値は10です。

##### TeratermStationとの整合性
- 1.0.0  
TeratermStation 1.0.4 or later
- 1.0.1  
TeratermStation 1.0.8 or later
