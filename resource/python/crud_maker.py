import javalang
import sqlparse
import pandas as pd
import xml.etree.ElementTree as ET

# Javaコード解析
def parse_java_code(file_path):
    with open(file_path, 'r') as file:
        code = file.read()
    return code

# SQL抽出と分類
def extract_and_classify_sql(java_code):
    queries = [line.strip() for line in java_code.splitlines() if any(op in line for op in ["SELECT", "INSERT", "UPDATE", "DELETE"])]
    classified = {"CREATE": [], "READ": [], "UPDATE": [], "DELETE": []}
    for query in queries:
        if "INSERT" in query:
            classified["CREATE"].append(query)
        elif "SELECT" in query:
            classified["READ"].append(query)
        elif "UPDATE" in query:
            classified["UPDATE"].append(query)
        elif "DELETE" in query:
            classified["DELETE"].append(query)
    return classified

# CRUD操作整理
def map_to_tables(classified_queries):
    operations = []
    for op, queries in classified_queries.items():
        for query in queries:
            table_name = query.split(" ")[3]  # 簡易テーブル名抽出
            operations.append({"Table": table_name, "Operation": op})
    return pd.DataFrame(operations)

# XMLファイル生成
def generate_crud_xml(df, output_file):
    root = ET.Element("CRUDDiagram")
    
    tables = df['Table'].unique()
    for table in tables:
        table_element = ET.SubElement(root, "Table", name=table)
        
        table_operations = df[df['Table'] == table]
        for _, row in table_operations.iterrows():
            ET.SubElement(table_element, "Operation", type=row['Operation'])
    
    tree = ET.ElementTree(root)
    tree.write(output_file, encoding='utf-8', xml_declaration=True)

# 実行
if __name__ == "__main__":
    # Javaファイルパスをユーザー入力で指定
    java_file_path = input("解析するJavaファイルのパスを入力してください: ").strip()
    output_file_path = "crud_diagram.xml"

    try:
        # Javaコード解析
        java_code = parse_java_code(java_file_path)
        
        # SQL分類
        classified = extract_and_classify_sql(java_code)

        # CRUD操作整理
        df = map_to_tables(classified)
        print(df)

        # XMLファイル生成
        generate_crud_xml(df, output_file_path)
        print(f"CRUD図をXML形式で出力しました: {output_file_path}")
    except FileNotFoundError:
        print(f"ファイルが見つかりません: {java_file_path}")
    except Exception as e:
        print(f"エラーが発生しました: {e}")
