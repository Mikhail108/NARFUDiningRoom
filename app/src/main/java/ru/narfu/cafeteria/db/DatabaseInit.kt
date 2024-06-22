package ru.narfu.cafeteria.db

import ru.narfu.cafeteria.App
import ru.narfu.cafeteria.db.model.Building
import ru.narfu.cafeteria.db.model.FoodType
import ru.narfu.cafeteria.db.model.Product

//Добавление данных в базу данных
object DatabaseInit {
    fun init() {
        val buildings = listOf(
            Building.fromNumber(1, "наб. Северное Двины, 17"),
            Building.fromNumber(3, "наб. Северное Двины, 22"),
            Building.fromNumber(11, "наб. Северное Двины, 2"),
            Building.fromNumber(12, "ул. Урицкого, 68, корп. 3"),
            Building.fromNumber(14, "ул. Смольный Буян, 1"),
        )
        val buildingsIds = App.database.buildingDao().insertAll(buildings)

        val foodTypes = listOf(
            FoodType.fromName("Салаты"),
            FoodType.fromName("Первые блюда"),
            FoodType.fromName("Гарниры"),
            FoodType.fromName("Основные блюда"),
            FoodType.fromName("Выпечка"),
            FoodType.fromName("Напитки"),
            FoodType.fromName("Соусы"),
            FoodType.fromName("Хлеб")
        )
        val foodTypesIds = App.database.foodTypeDao().insertAll(foodTypes)


        val products = listOf(
            // @formatter:off

            //
            // АУК-1
            //
            Product.fromData(buildingsIds[0], "Овощной салат", "помидоры, огурцы, сладкий перец, зелень.", "1/1", 70.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[0], "Винегрет", "свекла, картофель, морковь, соленые огурцы, зеленый горошек, лук, растительное масло.", "1/2", 80.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[0], "Оливье", "картофель, морковь, зеленый горошек, яйца, колбаса, майонез.", "1/3", 90.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[0], "Греческий салат", "помидоры, огурцы, сладкий перец, маслины, сыр фета, оливковое масло, зелень.", "1/4", 100.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[0], "Салат из морской капусты", "морская капуста, огурец, морковь,  яйца.", "1/5", 75.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[0], "Мясной салат", "отварная говядина, картофель, морковь, лук, зеленый горошек, майонез.", "1/6", 110.0, foodTypesIds[0]),

            Product.fromData(buildingsIds[0], "Борщ", "свекла, картофель, капуста, морковь, лук, томатная паста.", "1/7", 90.0, foodTypesIds[1]),
            Product.fromData(buildingsIds[0], "Гороховый суп", "горох, картофель, морковь, лук.", "1/8", 80.0, foodTypesIds[1]),
            Product.fromData(buildingsIds[0], "Суп с фрикадельками", "куриные фрикадельки, картофель, морковь, лук, зелень.", "1/9", 100.0, foodTypesIds[1]),
            Product.fromData(buildingsIds[0], "Картофельное пюре", "картофель, молоко, масло сливочное, соль.", "1/10", 40.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[0], "Рис отварной", "рис длиннозерный, соль.", "1/11", 30.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[0], "Гречка отварная", "гречневая крупа, соль.", "1/12", 35.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[0], "Овощное рагу", "картофель, морковь, лук, кабачки, баклажаны, болгарский перец, томатная паста, зелень, соль, перец.", "1/13", 50.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[0], "Картофель жаренный", "картофель, растительное масло для жарки, соль.", "1/14", 45.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[0], "Макароны отварные", "макаронные изделия, соль.", "1/15", 35.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[0], "Плов с курицей", "рис длиннозерный, куриное филе, репчатый лук, морковь, растительное масло, чеснок, куркума, перец, соль.", "1/16", 110.0, foodTypesIds[2]),

            Product.fromData(buildingsIds[0], "Котлета куриная", "куриное филе, панировочные сухари, яйца, молоко, соль, перец.", "1/18", 80.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[0], "Котлета рыбная", "рыбное филе трески, панировочные сухари, яйца, молоко, соль, перец.", "1/19", 75.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[0], "Котлета печеночная", "говяжья печень, лук репчатый, яйца, хпанировочные сухари, молоко, соль, перец.", "1/20", 86.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[0], "Биточки рыбные", "рыбное филе трески, панировочные сухари, яйца, лук репчатый, соль, перец.", "1/21", 65.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[0], "Дранники", "картофель отварной, мука, яйца, молоко, соль.", "1/22", 60.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[0], "Филе куринное, зепеченное с ветчиной", "куриное филе, ветчина, сыр, зелень, соль, перец.", "1/23", 115.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[0], "Сосиска отварная", "сосиска из говядины.", "1/24", 40.0, foodTypesIds[3]),

            Product.fromData(buildingsIds[0], "Пирожок с картошкой", "дрожжевое тесто, картофель отварной, лук репчатый, соль, перец.", "1/25", 35.0, foodTypesIds[4]),
            Product.fromData(buildingsIds[0], "Пирожок с капустой", "дрожжевое тесто, капуста свежая, лук репчатый, растительное масло, соль, перец.", "1/26", 35.0, foodTypesIds[4]),
            Product.fromData(buildingsIds[0], "Пирожок с мясом", "дрожжевое тесто, фарш мясной (говядина), лук репчатый, соль, перец.", "1/27", 40.0, foodTypesIds[4]),
            Product.fromData(buildingsIds[0], "Ватрушка с творогом", "дрожжевое тесто, творог, сахар, яйцо, масло сливочное, изюм.", "1/28", 45.0, foodTypesIds[4]),
            Product.fromData(buildingsIds[0], "Булочка сдобная", "дрожжевое тесто, молоко, яйца, сахар, масло сливочное, изюм.", "1/29", 30.0, foodTypesIds[4]),

            Product.fromData(buildingsIds[0], "Чай", "", "1/30", 30.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[0], "Кофе с молоком", "", "1/31", 40.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[0], "Сок апельсиновый", "", "1/32", 45.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[0], "Компот вишневый", "", "1/33", 30.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[0], "Морс брусничный", "", "1/34", 35.0, foodTypesIds[5]),

            Product.fromData(buildingsIds[0], "Майонез", "", "1/35", 15.0, foodTypesIds[6]),
            Product.fromData(buildingsIds[0], "Сметана", "", "1/36", 15.0, foodTypesIds[6]),
            Product.fromData(buildingsIds[0], "Соус томатный", "", "1/37", 20.0, foodTypesIds[6]),
            Product.fromData(buildingsIds[0], "Подлива к гарниру", "", "1/38", 10.0, foodTypesIds[6]),

            Product.fromData(buildingsIds[0], "Хлеб пшеничный", "", "1/39", 5.0, foodTypesIds[7]),
            Product.fromData(buildingsIds[0], "Хлеб ржаной", "", "1/40", 6.0, foodTypesIds[7]),

            //
            // АУК-3
            //

            Product.fromData(buildingsIds[1], "Овощной салат", "помидоры, огурцы, сладкий перец, зелень.", "3/1", 70.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[1], "Салат из свеклы со сметаной", "свекла отв., сметана", "3/2", 41.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[1], "Оливье", "картофель, морковь, зеленый горошек, яйца, колбаса, майонез.", "3/3", 90.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[1], "Салат из морской капусты", "морская капуста, кукуруза, яйца, майонез.", "3/4", 75.0, foodTypesIds[0]),

            Product.fromData(buildingsIds[1], "Борщ", "свекла, картофель, капуста, морковь, лук, томатная паста.", "3/5", 90.0, foodTypesIds[1]),
            Product.fromData(buildingsIds[1], "Солянка", "ветчина, лук , томат-паста, сметана, лимон, огурцы соленые, соль, перец, лавровый лис, петрушка", "3/6", 55.0, foodTypesIds[1]),
            Product.fromData(buildingsIds[1], "Гороховый суп", "горох, картофель, морковь, лук.", "3/7", 80.0, foodTypesIds[1]),

            Product.fromData(buildingsIds[1], "Макароны отварные", "макаронные изделия, соль.", "3/8", 35.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[1], "Картофельное пюре", "картофель, молоко, масло сливочное, соль.", "3/9", 40.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[1], "Гречка отварная", "гречневая крупа, соль.", "3/10", 35.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[1], "Картофель жаренный", "картофель, растительное масло для жарки, соль.", "3/11", 45.0, foodTypesIds[2]),

            Product.fromData(buildingsIds[1], "Котлета рыбная", "рыбное филе трески, панировочные сухари, яйца, молоко, соль, перец.", "3/12", 75.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[1], "Филе куринное, зепеченное с ветчиной", "куриное филе, ветчина, сыр, зелень, соль, перец.", "3/13", 115.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[1], "Сосиска отварная", "сосиска из говядины.", "3/14", 40.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[1], "Дранники", "картофель отварной, мука, яйца, молоко, соль.", "3/15", 60.0, foodTypesIds[3]),

            Product.fromData(buildingsIds[1], "Чай черный", "", "3/16", 30.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[1], "Кофе черный", "", "3/17", 40.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[1], "Сок яблочный", "", "3/18", 45.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[1], "Компот вишневый", "", "3/19", 30.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[1], "Морс клюквенный", "", "3/20", 35.0, foodTypesIds[5]),

            //
            // АУК-11
            //

            Product.fromData(buildingsIds[2], "Овощной салат", "помидоры, огурцы, сладкий перец, зелень.", "11/1", 70.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[2], "Винегрет", "свекла, картофель, морковь, соленые огурцы, зеленый горошек, лук, растительное масло.", "11/2", 80.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[2], "Оливье", "картофель, морковь, зеленый горошек, яйца, колбаса, майонез.", "11/3", 90.0, foodTypesIds[0]),

            Product.fromData(buildingsIds[2], "Борщ", "свекла, картофель, капуста, морковь, лук, томатная паста.", "11/4", 95.0, foodTypesIds[1]),

            Product.fromData(buildingsIds[2], "Рис отварной", "рис длиннозерный, вода, соль.", "11/5", 40.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[2], "Картофельное пюре", "картофель отварной, молоко, масло сливочное, соль.", "11/6", 45.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[2], "Картофель жаренный", "картофель, растительное масло для жарки, соль.", "11/7", 50.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[2], "Макароны отварные", "макаронные изделия, соль.", "11/8", 40.0, foodTypesIds[2]),

            Product.fromData(buildingsIds[2], "Котлета куриная", "куриное филе, панировочные сухари, яйца, молоко, соль, перец.", "11/9", 120.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[2], "Филе куринное, зепеченное с ветчиной", "куриное филе, ветчина, сыр, зелень, соль, перец.", "11/10", 110.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[2], "Биточки рыбные", "рыбное филе трески, панировочные сухари, яйца, лук репчатый, соль, перец.", "11/11", 130.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[2], "Дранники", "картофель отварной, мука, яйца, молоко, соль.", "11/12", 100.0, foodTypesIds[3]),

            Product.fromData(buildingsIds[2], "Пирожок с картошкой", "дрожжевое тесто, картофель отварной, лук репчатый, соль, перец.", "11/13", 35.0, foodTypesIds[4]),
            Product.fromData(buildingsIds[2], "Пирожок с капустой", "дрожжевое тесто, капуста свежая, лук репчатый, растительное масло, соль, перец.", "11/14", 35.0, foodTypesIds[4]),
            Product.fromData(buildingsIds[2], "Булочка сдобная", "дрожжевое тесто, молоко, яйца, сахар, масло сливочное, изюм.", "11/15", 30.0, foodTypesIds[4]),
            Product.fromData(buildingsIds[2], "Ватрушка с творогом", "дрожжевое тесто, творог, сахар, яйцо, масло сливочное, изюм.", "11/16", 45.0, foodTypesIds[4]),

            Product.fromData(buildingsIds[2], "Компот вишневый", "", "11/17", 30.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[2], "Чай ", "", "11/18", 30.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[2], "Сок апельсиновый", "", "11/19", 45.0, foodTypesIds[5]),

            Product.fromData(buildingsIds[2], "Майонез", "", "11/20", 15.0, foodTypesIds[6]),
            Product.fromData(buildingsIds[2], "Сметана", "", "11/21", 15.0, foodTypesIds[6]),
            Product.fromData(buildingsIds[2], "Подлива к гарниру", "", "11/22", 10.0, foodTypesIds[6]),

            Product.fromData(buildingsIds[2], "Хлеб пшеничный", "", "11/23", 5.0, foodTypesIds[7]),
            Product.fromData(buildingsIds[2], "Хлеб ржаной", "", "11/24", 6.0, foodTypesIds[7]),

            //
            // АУК-12
            //

            Product.fromData(buildingsIds[3], "Салат из капусты с морковью", "капуста б/к, морковь, масло растю., сахар, соль, уксус", "12/1", 38.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[3], "Салат из свеклы со сметаной", "свекла отв., сметана", "12/2", 41.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[3], "Валери", "курица, огурец св., кальмары, яйцо, майонез", "12/3", 59.0, foodTypesIds[0]),

            Product.fromData(buildingsIds[3], "Борщ", "свекла, картофель, капуста, морковь, лук, томатная паста.", "12/4", 90.0, foodTypesIds[1]),
            Product.fromData(buildingsIds[3], "Солянка", "ветчина, лук , томат-паста, сметана, лимон, огурцы соленые, соль, перец, лавровый лис, петрушка", "12/5", 55.0, foodTypesIds[1]),
            Product.fromData(buildingsIds[3], "Куриный суп с яйцом", "курица, картофель, морковь, лук реп., яйцо, масло раст., укроп св., соль, перец черный молотый", "12/6", 60.0, foodTypesIds[1]),

            Product.fromData(buildingsIds[3], "Картофельное пюре", "картофель, молоко, масло сливочное, соль.", "12/7", 40.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[3], "Гречка отварная", "гречневая крупа, соль.", "12/8", 35.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[3], "Макароны отварные", "макаронные изделия, соль.", "12/9", 35.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[3], "Соус томатный", "топ. паста, мука пш., масло раст.", "12/10", 13.0, foodTypesIds[2]),

            Product.fromData(buildingsIds[3], "Биточки рыбные", "минтай, хлеб пшен., лук репч, яйцо", "12/11", 65.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[3], "Плов со свининой", "рис, свинина, лук, морковь, масло раст.", "12/12", 114.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[3], "Котлета печеночная", "говяжья печень, лук репчатый, яйца, хпанировочные сухари, молоко, соль, перец.", "12/13", 86.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[3], "Сосиски с макаронами и томатом", "макароны отв., сосиски, том. паста", "12/14", 95.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[3], "Дранники", "картофель отварной, мука, яйца, молоко, соль.", "12/15", 60.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[3], "Филе куринное, зепеченное с ветчиной", "куриное филе, ветчина, сыр, зелень, соль, перец.", "12/16", 115.0, foodTypesIds[3]),

            Product.fromData(buildingsIds[3], "Компот вишневый", "", "12/17", 29.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[3], "Компот сливовый", "", "12/18", 29.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[3], "Сок томатный", "", "12/19", 30.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[3], "Чай Гринфилд", "", "12/20", 19.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[3], "Сахар", "", "12/21", 6.0, foodTypesIds[5]),

            //
            // АУК-14
            //

            Product.fromData(buildingsIds[4], "Салат из морской капусты", "морская капуста, кукуруза, яйца, майонез.", "14/1", 75.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[4], "Винегрет", "свекла, картофель, морковь, соленые огурцы, зеленый горошек, лук, растительное масло.", "14/2", 80.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[4], "Оливье", "картофель, морковь, зеленый горошек, яйца, колбаса, майонез.", "14/3", 90.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[4], "Мясной салат", "отварная говядина, картофель, морковь, лук, зеленый горошек, майонез.", "14/4", 110.0, foodTypesIds[0]),
            Product.fromData(buildingsIds[4], "Греческий салат", "помидоры, огурцы, сладкий перец, маслины, сыр фета, оливковое масло, зелень.", "14/5", 100.0, foodTypesIds[0]),

            Product.fromData(buildingsIds[4], "Борщ", "свекла, картофель, капуста, морковь, лук, томатная паста.", "14/6", 90.0, foodTypesIds[1]),
            Product.fromData(buildingsIds[4], "Гороховый суп", "горох, картофель, морковь, лук.", "14/7", 80.0, foodTypesIds[1]),
            Product.fromData(buildingsIds[4], "Суп-гуляш из говядины", "говядина, перец болгарский, лук репчатый, картофель, чеснок, томатная паста", "14/8", 85.0, foodTypesIds[1]),

            Product.fromData(buildingsIds[4], "Картофельное пюре", "картофель, молоко, масло сливочное, соль.", "14/9", 40.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[4], "Рис отварной", "рис длиннозерный, соль.", "14/10", 30.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[4], "Гречка отварная", "гречневая крупа, соль.", "14/11", 35.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[4], "Плов с курицей", "рис длиннозерный, куриное филе, репчатый лук, морковь, растительное масло, чеснок, куркума, перец, соль.", "14/12", 110.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[4], "Картофель жаренный", "картофель, растительное масло для жарки, соль.", "14/13", 45.0, foodTypesIds[2]),
            Product.fromData(buildingsIds[4], "Макароны отварные", "макаронные изделия, соль.", "14/14", 35.0, foodTypesIds[2]),

            Product.fromData(buildingsIds[4], "Котлета куриная", "куриное филе, панировочные сухари, яйца, молоко, соль, перец.", "14/15", 80.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[4], "Жаркое по-деревенски", "Свиная корейка, сало свиное, масло сл., картофель, перец болг., морковь, лук репч., чеснок", "14/16", 65.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[4], "Котлета рыбная", "рыбное филе трески, панировочные сухари, яйца, молоко, соль, перец.", "14/17", 75.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[4], "Котлета печеночная", "говяжья печень, лук репчатый, яйца, хпанировочные сухари, молоко, соль, перец.", "14/18", 86.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[4], "Сосиска отварная", "сосиска из говядины.", "14/19", 40.0, foodTypesIds[3]),
            Product.fromData(buildingsIds[4], "Картофельные драники", "картофель, яйца, соль, перец молотый", "14/20", 55.0, foodTypesIds[3]),

            Product.fromData(buildingsIds[4], "Чай черный", "", "14/21", 30.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[4], "Кофе черный", "", "14/22", 40.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[4], "Сок яблочный", "", "14/23", 45.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[4], "Компот вишневый", "", "14/24", 30.0, foodTypesIds[5]),
            Product.fromData(buildingsIds[4], "Морс клюквенный", "", "14/25", 35.0, foodTypesIds[5]),

            Product.fromData(buildingsIds[4], "Майонез", "", "14/26", 15.0, foodTypesIds[6]),
            Product.fromData(buildingsIds[4], "Сметана", "", "14/27", 15.0, foodTypesIds[6]),
            Product.fromData(buildingsIds[4], "Соус томатный", "", "14/28", 20.0, foodTypesIds[6]),

            Product.fromData(buildingsIds[4], "Хлеб пшеничный", "", "14/29", 5.0, foodTypesIds[7]),
            Product.fromData(buildingsIds[4], "Хлеб ржаной", "", "14/30", 6.0, foodTypesIds[7]),

            // @formatter:on
        )

        App.database.productDao().insertAll(products)
    }
}