# Консольная утилита для поиска файлов в файловой системе.

Программа ищет файлы в заданном каталоге и подкаталогах.
Имя файла может задаваться: целиком, по маске, по регулярному выражению.

Параметры должны передаваться в командной строке.
Результаты записываются в файл.

Ключи:
- -d - директория с которой начинается поиск.
- -n - имя файла, маска, либо регулярное выражение.
- -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
- -o - результат записать в файл.

Пример запуска:  -d=c:  -n=*.?xt -t=mask -o=log.txt