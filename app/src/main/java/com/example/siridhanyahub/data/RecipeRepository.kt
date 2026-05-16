package com.example.siridhanyahub.data

data class Recipe(
    val id: String,
    val title: String,
    val category: String,
    val milletType: String,
    val image: String,
    val shortDescription: String,
    val cookingTime: String,
    val difficulty: String,
    val calories: String,
    val servings: Int,
    val healthTags: List<String>,
    val ingredients: List<Pair<String, String>>,
    val cookingSteps: List<Pair<String, String>>,
    val healthBenefits: List<String>
)

object RecipeRepository {

    private val commonBenefits = listOf(
        "Provides sustained energy and prevents blood sugar spikes.",
        "Rich in dietary fiber which aids in healthy digestion.",
        "Contains essential minerals and micronutrients for overall wellness."
    )

    private fun breakfastIngredients() = listOf(
        "Millet" to "1 cup",
        "Water" to "2 cups",
        "Salt" to "To taste",
        "Ghee" to "1 tsp",
        "Curry Leaves" to "Few"
    )

    private fun lunchIngredients() = listOf(
        "Millet" to "1 cup",
        "Dal/Lentils" to "1/2 cup",
        "Mixed Vegetables" to "1 cup",
        "Spices" to "To taste",
        "Ghee/Oil" to "2 tbsp"
    )

    private fun snacksIngredients() = listOf(
        "Millet Flour" to "1 cup",
        "Oil" to "For frying/baking",
        "Salt & Spices" to "To taste"
    )

    private fun dessertIngredients() = listOf(
        "Millet" to "1 cup",
        "Jaggery" to "3/4 cup",
        "Milk or Coconut Milk" to "2 cups",
        "Cardamom Powder" to "1/2 tsp",
        "Nuts" to "2 tbsp"
    )

    private fun savorySteps() = listOf(
        "Soak" to "Wash and soak millet for 30 minutes for better digestion.",
        "Temper" to "Heat ghee/oil, add mustard seeds, cumin, and curry leaves.",
        "Sauté" to "Add aromatics and vegetables, cook until tender.",
        "Simmer" to "Add soaked millet and water, bring to boil.",
        "Finish" to "Cover and cook on low heat until water is absorbed."
    )

    private fun snackSteps() = listOf(
        "Prepare Dough/Batter" to "Mix millet flour with spices and a little water.",
        "Shape" to "Form into desired snack shapes (balls, flat discs, etc.).",
        "Cook" to "Deep fry, bake, or pan-roast until golden brown.",
        "Drain" to "Remove excess oil using a paper towel.",
        "Serve" to "Serve hot with chutney or tea."
    )

    private fun dessertSteps() = listOf(
        "Preparation" to "Roast the millet gently until aromatic.",
        "Sweetener" to "Melt jaggery with a little water to form a light syrup.",
        "Cooking" to "Cook millet in milk or water until soft.",
        "Combine" to "Mix in the jaggery syrup and simmer.",
        "Garnish" to "Add roasted nuts and cardamom powder. Serve warm."
    )

    val recipes = listOf(
        // BREAKFAST (15)
        Recipe("B01", "Ragi Idli", "Breakfast", "Finger Millet (Ragi)", "🫓", "A healthy twist on traditional idli.", "20 min", "Easy", "150 kcal", 2, listOf("Protein", "Low GI", "Probiotic"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B02", "Navane Upma", "Breakfast", "Foxtail Millet (Navane)", "🌾", "Wholesome and quick breakfast upma.", "15 min", "Easy", "200 kcal", 2, listOf("High Fiber", "Energy"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B03", "Millet Pongal", "Breakfast", "Mixed Millets", "🍚", "Warm, comforting savory pongal.", "25 min", "Medium", "280 kcal", 2, listOf("Protein", "Energy"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B04", "Ragi Dosa", "Breakfast", "Finger Millet (Ragi)", "🫓", "Crispy and instant Ragi dosa.", "15 min", "Easy", "180 kcal", 2, listOf("Calcium", "Gluten Free"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B05", "Sajje Roti Breakfast", "Breakfast", "Pearl Millet (Sajje)", "🫓", "Iron-rich roti to start the day.", "25 min", "Medium", "220 kcal", 2, listOf("Iron Rich", "Fiber"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B06", "Millet Vegetable Uttapam", "Breakfast", "Mixed Millets", "🫓", "Thick savory pancake loaded with veggies.", "20 min", "Easy", "210 kcal", 2, listOf("Vitamins", "Low GI"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B07", "Ragi Mudde Bowl", "Breakfast", "Finger Millet (Ragi)", "🫓", "Traditional energy bowl.", "30 min", "Medium", "250 kcal", 2, listOf("Endurance", "Calcium"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B08", "Foxtail Millet Poha", "Breakfast", "Foxtail Millet (Navane)", "🥣", "Light and fluffy millet poha.", "15 min", "Easy", "190 kcal", 2, listOf("Light", "Digestible"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B09", "Millet Lemon Rice", "Breakfast", "Mixed Millets", "🍚", "Tangy and refreshing lemon rice.", "20 min", "Easy", "230 kcal", 2, listOf("Vitamin C", "Energy"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B10", "Millet Khara Bath", "Breakfast", "Proso Millet (Baragu)", "🥣", "Spicy semolina-style millet bath.", "20 min", "Medium", "240 kcal", 2, listOf("Spicy", "Fiber"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B11", "Millet Akki Roti", "Breakfast", "Mixed Millets", "🫓", "Spiced flatbread with millet flour.", "25 min", "Medium", "210 kcal", 2, listOf("Iron", "Filling"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B12", "Baragu Vegetable Pulao", "Breakfast", "Proso Millet (Baragu)", "🍚", "Quick morning pulao with veggies.", "25 min", "Easy", "260 kcal", 2, listOf("Balanced", "Vitamins"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B13", "Millet Curd Rice", "Breakfast", "Little Millet (Saame)", "🍚", "Cooling probiotic curd rice.", "15 min", "Easy", "180 kcal", 2, listOf("Probiotic", "Cooling"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B14", "Little Millet Idiyappam", "Breakfast", "Little Millet (Saame)", "🫓", "Steamed string hoppers.", "30 min", "Medium", "170 kcal", 2, listOf("Steamed", "Light"), breakfastIngredients(), savorySteps(), commonBenefits),
        Recipe("B15", "Barnyard Millet Upma", "Breakfast", "Barnyard Millet (Oodalu)", "🥣", "Fasting-friendly upma.", "15 min", "Easy", "160 kcal", 2, listOf("Fasting Safe", "Low Cal"), breakfastIngredients(), savorySteps(), commonBenefits),

        // LUNCH (15)
        Recipe("L01", "Millet Bisi Bele Bath", "Lunch", "Mixed Millets", "🍲", "Spicy and tangy lentil millet mix.", "40 min", "Medium", "350 kcal", 2, listOf("Balanced", "Protein"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L02", "Sajje Roti Meal", "Lunch", "Pearl Millet (Sajje)", "🫓", "Complete meal with pearl millet roti.", "35 min", "Medium", "320 kcal", 2, listOf("Iron Rich", "Traditional"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L03", "Millet Khichdi", "Lunch", "Mixed Millets", "🍲", "Comforting one-pot khichdi.", "30 min", "Easy", "290 kcal", 2, listOf("Comfort Food", "Digestible"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L04", "Ragi Mudde with Sambar", "Lunch", "Finger Millet (Ragi)", "🍛", "Classic Karnataka lunch staple.", "45 min", "Hard", "400 kcal", 2, listOf("Calcium", "Protein"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L05", "Foxtail Millet Pulao", "Lunch", "Foxtail Millet (Navane)", "🍚", "Fragrant and fluffy pulao.", "30 min", "Easy", "310 kcal", 2, listOf("High Fiber", "Vegan"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L06", "Millet Sambar Rice", "Lunch", "Mixed Millets", "🍲", "Millet cooked directly in sambar.", "35 min", "Medium", "330 kcal", 2, listOf("Protein", "Spicy"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L07", "Kodo Millet Fried Rice", "Lunch", "Kodo Millet (Harka)", "🍚", "Indo-Chinese style healthy fried rice.", "20 min", "Easy", "280 kcal", 2, listOf("Low GI", "Quick"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L08", "Millet Curd Rice Deluxe", "Lunch", "Little Millet (Saame)", "🍚", "Rich curd rice with pomegranate.", "15 min", "Easy", "240 kcal", 2, listOf("Probiotic", "Cooling"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L09", "Millet Vegetable Biryani", "Lunch", "Mixed Millets", "🍛", "Rich and aromatic millet biryani.", "45 min", "Hard", "380 kcal", 2, listOf("Festive", "Spices"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L10", "Navane Rice Bowl", "Lunch", "Foxtail Millet (Navane)", "🥗", "Modern healthy bowl with greens.", "25 min", "Easy", "290 kcal", 2, listOf("Modern", "Balanced"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L11", "Millet Dal Khichdi", "Lunch", "Mixed Millets", "🍲", "Protein-packed daily lunch.", "30 min", "Easy", "300 kcal", 2, listOf("Protein", "Everyday"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L12", "Baragu Coconut Rice", "Lunch", "Proso Millet (Baragu)", "🍚", "Mildly spiced fresh coconut rice.", "25 min", "Easy", "340 kcal", 2, listOf("Healthy Fats", "Vegan"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L13", "Millet Tamarind Rice", "Lunch", "Mixed Millets", "🍚", "Tangy puliyogare with millets.", "30 min", "Medium", "320 kcal", 2, listOf("Tangy", "Traditional"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L14", "Millet Rasam Rice", "Lunch", "Little Millet (Saame)", "🍲", "Soothing and spicy rasam with millet.", "25 min", "Easy", "260 kcal", 2, listOf("Immunity", "Warm"), lunchIngredients(), savorySteps(), commonBenefits),
        Recipe("L15", "Millet Palak Rice", "Lunch", "Mixed Millets", "🍚", "Iron-rich spinach and millet dish.", "30 min", "Medium", "290 kcal", 2, listOf("Iron", "Greens"), lunchIngredients(), savorySteps(), commonBenefits),

        // SNACKS (15)
        Recipe("S01", "Ragi Murukku", "Snacks", "Finger Millet (Ragi)", "🍘", "Crunchy deep-fried savory spiral.", "35 min", "Medium", "200 kcal", 2, listOf("Crunchy", "Calcium"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S02", "Millet Cutlet", "Snacks", "Mixed Millets", "🥙", "Pan-fried vegetable and millet patties.", "30 min", "Easy", "180 kcal", 2, listOf("Low Fat", "Veggies"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S03", "Sajje Chakkuli", "Snacks", "Pearl Millet (Sajje)", "🍘", "Crispy festive snack.", "40 min", "Medium", "220 kcal", 2, listOf("Iron", "Traditional"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S04", "Millet Masala Vada", "Snacks", "Mixed Millets", "🍘", "Spicy and crispy lentil-millet fritters.", "30 min", "Medium", "240 kcal", 2, listOf("Protein", "Spicy"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S05", "Ragi Chips", "Snacks", "Finger Millet (Ragi)", "🍘", "Baked healthy ragi crisps.", "25 min", "Easy", "150 kcal", 2, listOf("Baked", "Low Cal"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S06", "Millet Nippattu", "Snacks", "Mixed Millets", "🍘", "Spicy, crispy rice-like crackers.", "35 min", "Medium", "210 kcal", 2, listOf("Spicy", "Crunchy"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S07", "Foxtail Millet Pakoda", "Snacks", "Foxtail Millet (Navane)", "🍘", "Onion pakodas with a millet twist.", "20 min", "Easy", "250 kcal", 2, listOf("Fried", "Snack"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S08", "Millet Energy Balls", "Snacks", "Mixed Millets", "🍘", "No-bake sweet and nutty energy bites.", "15 min", "Easy", "190 kcal", 2, listOf("No Bake", "Energy"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S09", "Millet Sandwich Toast", "Snacks", "Mixed Millets", "🥪", "Toast made from millet bread.", "10 min", "Easy", "160 kcal", 2, listOf("Quick", "Modern"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S10", "Millet Khakhra", "Snacks", "Mixed Millets", "🫓", "Thin, crispy roasted flatbread.", "30 min", "Medium", "140 kcal", 2, listOf("Roasted", "Low Fat"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S11", "Millet Corn Tikki", "Snacks", "Mixed Millets", "🥙", "Sweet corn and millet pan-fried patties.", "25 min", "Easy", "170 kcal", 2, listOf("Kids Friendly", "Veggies"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S12", "Millet Spring Roll", "Snacks", "Mixed Millets", "🌯", "Spring rolls with millet stuffing.", "40 min", "Hard", "260 kcal", 2, listOf("Fusion", "Crunchy"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S13", "Millet Cheese Balls", "Snacks", "Mixed Millets", "🍘", "Cheesy millet bites for kids.", "25 min", "Medium", "280 kcal", 2, listOf("Cheese", "Kids Friendly"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S14", "Millet Bhel", "Snacks", "Mixed Millets", "🥗", "Puffed millet tossed with tangy chutneys.", "10 min", "Easy", "130 kcal", 2, listOf("Tangy", "Low Cal"), snacksIngredients(), snackSteps(), commonBenefits),
        Recipe("S15", "Ragi Crackers", "Snacks", "Finger Millet (Ragi)", "🍘", "Savory baked ragi crackers.", "30 min", "Medium", "150 kcal", 2, listOf("Baked", "Calcium"), snacksIngredients(), snackSteps(), commonBenefits),

        // DESSERT (15)
        Recipe("D01", "Ragi Halwa", "Dessert", "Finger Millet (Ragi)", "🍮", "Rich, glossy sweet pudding.", "30 min", "Medium", "320 kcal", 2, listOf("Rich", "Calcium"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D02", "Millet Payasam", "Dessert", "Mixed Millets", "🍵", "Creamy traditional milk-based sweet.", "35 min", "Medium", "290 kcal", 2, listOf("Festive", "Sweet"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D03", "Navane Ladoo", "Dessert", "Foxtail Millet (Navane)", "🟡", "Sweet spherical treats with jaggery.", "25 min", "Easy", "250 kcal", 2, listOf("Energy", "Iron"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D04", "Ragi Chocolate Cake", "Dessert", "Finger Millet (Ragi)", "🎂", "Healthy baked chocolate cake.", "45 min", "Hard", "350 kcal", 2, listOf("Baked", "Kids Friendly"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D05", "Millet Kheer", "Dessert", "Little Millet (Saame)", "🍵", "Slow-cooked millet in sweet milk.", "40 min", "Medium", "280 kcal", 2, listOf("Traditional", "Calcium"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D06", "Millet Kesari Bath", "Dessert", "Proso Millet (Baragu)", "🍮", "Saffron infused sweet semolina substitute.", "20 min", "Easy", "310 kcal", 2, listOf("Quick", "Sweet"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D07", "Sajje Sweet Roti", "Dessert", "Pearl Millet (Sajje)", "🫓", "Jaggery stuffed pearl millet flatbread.", "35 min", "Medium", "290 kcal", 2, listOf("Iron Rich", "Traditional"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D08", "Millet Dry Fruit Barfi", "Dessert", "Mixed Millets", "🍘", "Dense fudge with nuts and seeds.", "40 min", "Medium", "340 kcal", 2, listOf("Energy", "Nuts"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D09", "Millet Coconut Ladoo", "Dessert", "Mixed Millets", "🟡", "Coconut and millet sweet balls.", "20 min", "Easy", "270 kcal", 2, listOf("Quick", "Festive"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D10", "Millet Banana Pancake", "Dessert", "Mixed Millets", "🥞", "Sweet breakfast or dessert pancakes.", "15 min", "Easy", "220 kcal", 2, listOf("Fruits", "Kids Friendly"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D11", "Millet Sweet Pongal", "Dessert", "Mixed Millets", "🍮", "Jaggery sweetened festive pongal.", "30 min", "Medium", "330 kcal", 2, listOf("Festive", "Rich"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D12", "Ragi Brownie", "Dessert", "Finger Millet (Ragi)", "🍫", "Fudgy brownies made without maida.", "40 min", "Medium", "310 kcal", 2, listOf("Baked", "Modern"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D13", "Millet Pudding", "Dessert", "Mixed Millets", "🍮", "Smooth chilled dessert.", "20 min", "Easy", "260 kcal", 2, listOf("Chilled", "Creamy"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D14", "Millet Jaggery Cookies", "Dessert", "Mixed Millets", "🍪", "Crispy cookies sweetened with jaggery.", "30 min", "Medium", "240 kcal", 2, listOf("Baked", "Snack"), dessertIngredients(), dessertSteps(), commonBenefits),
        Recipe("D15", "Millet Fruit Custard", "Dessert", "Mixed Millets", "🍧", "Custard base thickened with millet flour.", "25 min", "Easy", "210 kcal", 2, listOf("Fruits", "Cooling"), dessertIngredients(), dessertSteps(), commonBenefits)
    )

    fun getRecipesByCategory(category: String): List<Recipe> {
        return recipes.filter { it.category.equals(category, ignoreCase = true) }
    }

    fun getRecipeById(id: String): Recipe? {
        return recipes.find { it.id == id }
    }
    
    fun getRecipeByTitle(title: String): Recipe? {
        return recipes.find { it.title.equals(title, ignoreCase = true) }
    }

    fun searchRecipes(query: String): List<Recipe> {
        return recipes.filter { 
            it.title.contains(query, ignoreCase = true) || 
            it.milletType.contains(query, ignoreCase = true) ||
            it.category.contains(query, ignoreCase = true)
        }
    }
}
