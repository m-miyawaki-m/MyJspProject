// pythonの実行環境作成
python -m venv venv

// ライブラリインストール
pip install javalang sqlparse pandas graphviz

// ライブラリの再利用化メモ
pip freeze > requirements.txt

// 必要ライブラリの再インストール
pip install -r requirements.txt

