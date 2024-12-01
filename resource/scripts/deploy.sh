#!/bin/bash

# 設定部分
WAR_DIR="/home/ec2-user/deploy"  # WARファイルが格納されているディレクトリ
TOMCAT_DIR="/opt/tomcat/tomcat10/apache-tomcat-10.1.33"  # Tomcatのルートディレクトリ
WEBAPPS_DIR="$TOMCAT_DIR/webapps"  # Tomcatのwebappsディレクトリ
TOMCAT_USER="tomcat"  # Tomcatを実行するユーザー
TOMCAT_GROUP="tomcat"  # Tomcatのグループ

# WARファイル一覧を取得
WAR_FILES=($(ls $WAR_DIR/*.war 2>/dev/null))

# WARファイルが見つからない場合の処理
if [ ${#WAR_FILES[@]} -eq 0 ]; then
  echo "ERROR: $WAR_DIR にWARファイルが見つかりません。"
  exit 1
fi

# WARファイルを一覧表示
echo "以下のWARファイルが見つかりました:"
for i in "${!WAR_FILES[@]}"; do
  echo "$((i + 1))) $(basename "${WAR_FILES[$i]}")"
done

# ユーザーに選択を促す
echo -n "デプロイするWARファイルの番号を選択してください: "
read -r CHOICE

# 入力が正しいか確認
if [[ ! $CHOICE =~ ^[0-9]+$ ]] || [ "$CHOICE" -lt 1 ] || [ "$CHOICE" -gt "${#WAR_FILES[@]}" ]; then
  echo "ERROR: 無効な選択です。"
  exit 1
fi

# 選択されたWARファイル
SELECTED_WAR="${WAR_FILES[$((CHOICE - 1))]}"

echo "選択されたWARファイル: $(basename "$SELECTED_WAR")"

# 1. Tomcatを停止
echo "==> Tomcatを停止中..."
sudo systemctl stop tomcat || {
  echo "ERROR: Tomcatを停止できませんでした。";
  exit 1;
}

# 2. 古いWARファイルと展開済みのディレクトリを削除
WAR_NAME=$(basename "$SELECTED_WAR")
APP_NAME="${WAR_NAME%.*}"
echo "==> 古いWARファイルと展開済みのディレクトリを削除中..."
sudo rm -rf "$WEBAPPS_DIR/$WAR_NAME" "$WEBAPPS_DIR/$APP_NAME"

# 3. 新しいWARファイルをコピー
echo "==> 新しいWARファイルをコピー中..."
sudo cp "$SELECTED_WAR" "$WEBAPPS_DIR/" || {
  echo "ERROR: WARファイルをコピーできませんでした。";
  exit 1;
}

# 4. パーミッションの設定
echo "==> パーミッションを設定中..."
sudo chown $TOMCAT_USER:$TOMCAT_GROUP "$WEBAPPS_DIR/$WAR_NAME"

# 5. Tomcatを起動
echo "==> Tomcatを起動中..."
sudo systemctl start tomcat || {
  echo "ERROR: Tomcatを起動できませんでした。";
  exit 1;
}

echo "==> デプロイが完了しました！"
# パブリックIPアドレスを取得
PUBLIC_IP=$(curl -s http://checkip.amazonaws.com)

# URLを表示
if [ -n "$PUBLIC_IP" ]; then
  echo "アプリケーションURL: http://$PUBLIC_IP"
else
  echo "ERROR: パブリックIPアドレスを取得できませんでした。"
fi