import sqlite3

TABLE_NAME = 'Example'
FIELDS = 'ID, FirstField, SecondField'


def main(db_name):
    with sqlite3.connect(db_name) as connection:
        cursor = connection.cursor()
        if TABLE_NAME in (row[0] for row in
                          cursor.execute('SELECT name FROM sqlite_master').fetchall()):
            cursor.execute(f'DROP TABLE {TABLE_NAME}')
        cursor.execute(f'CREATE TABLE {TABLE_NAME}({FIELDS})')
        cursor.execute(f'INSERT INTO {TABLE_NAME}({FIELDS}) VALUES(?, ?, ?)',
                       [1, '2', 3])
        connection.commit()
        for row in cursor.execute(f'SELECT * FROM {TABLE_NAME}'):
            print(row)


if __name__ == '__main__':
    import sys
    from pathlib import Path
    if len(sys.argv) != 2:
        print(f'usage: {Path(sys.argv[0]).name} dbname')
        exit(1)
    main(sys.argv[1])
