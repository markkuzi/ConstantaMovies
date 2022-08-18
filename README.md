# ConstantaMovies
Тестовое задание для компании Constanta

Необходимо создать Android приложение, которое отображает список фильмов, который доступен в формате JSON по ссылке [films.json](https://raw.githubusercontent.com/constanta-android-dev/intership-wellcome-task/main/films.json)

Требования:

для отображения списка фильмов нужно использовать Fragment+RecyclerView
должен быть презентационный слой, реализованный по паттерну MVVM, который при запуске фрагмента получает список фильмов через репозиторий и передает фрагменту
для загрузки данных из сети использовать библиотеки Retrofit+Gson
фильмы должны быть сортированы по году выпуска
каждый элемент списка должен содержать следующую информацию:
заголовок в виде "<Название> (<год выпуска>)"

имя режиссера в формате "Фамилия И.О."

имена и фамилии актеров без дубликатов

при нажатии на элемент RecyclerView должно отобразиться диалоговое окно с надписью: "Фильм <Название> был нажат"
