package ie.tus.mad.k00271321assignment2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import ie.tus.mad.k00271321assignment2.data.MenuItem
import ie.tus.mad.k00271321assignment2.home.HomeScreen
import ie.tus.mad.k00271321assignment2.login.LoginScreen
import ie.tus.mad.k00271321assignment2.menu.MenuScreen
import ie.tus.mad.k00271321assignment2.navigation.BuildNavigationGraph
import ie.tus.mad.k00271321assignment2.ui.theme.TUSEatsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TUSEatsTheme {
                BuildNavigationGraph()
//                val allMenuItems = listOf(
//                    MenuItem(1,"non-vegan","Spice Bag", "Serving from 12pm - 4pm", "€5.50", "https://media-cdn2.greatbritishchefs.com/media/3cfh22lf/img63085.whqc_768x512q80.jpg"),
//                    MenuItem(2,"non-vegan","Fish & Chips", "Serving from 12pm - 4pm", "€4.50", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Fish_and_chips_blackpool.jpg/1200px-Fish_and_chips_blackpool.jpg"),
//                    MenuItem(3,"non-vegan","Beef Stew", "Serving from 12pm - 4pm", "€5.30", "https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2023/09/Beef-Stew-main.jpg"),
//                    MenuItem(4,"non-vegan","Steak & Mash", "Serving from 12pm - 4pm", "€4.70", "https://img.taste.com.au/0NpUpm-p/taste/2016/11/steak-diane-with-chive-mash-85635-1.jpeg"),
//                    MenuItem(5,"non-vegan","Southern Fried Chicken", "Serving from 12pm - 4pm", "€5.35", "https://www.mccain.co.uk/custom/uploads/2023/04/Southern-Fried-Chicken-top.jpg"),
//                    MenuItem(6,"non-vegan","Carbonara", "Serving from 12pm - 4pm", "€4.20", "https://www.allrecipes.com/thmb/Vg2cRidr2zcYhWGvPD8M18xM_WY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/11973-spaghetti-carbonara-ii-DDMFS-4x3-6edea51e421e4457ac0c3269f3be5157.jpg"),
//                    MenuItem(7,"non-vegan","Cheese Burger", "Serving from 12pm - 4pm", "€5.50", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRvk78dJy_ftdqYjlZud1rhv2FDEgksmbkEA&s"),
//                    MenuItem(8,"non-vegan","Chicken Burger", "Serving from 12pm - 4pm", "€4.50", "https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/air_fryer_cblt_burger_03517_16x9.jpg"),
//                    MenuItem(9,"non-vegan","Pizza", "Serving from 12pm - 4pm", "€5.90", "https://www.allrecipes.com/thmb/aefJMDXKqs42oAP71dQuYf_-Qdc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/6776_Pizza-Dough_ddmfs_4x3_1724-fd91f26e0bd6400a9e89c6866336532b.jpg"),
//                    MenuItem(10,"non-vegan","Lasagne", "Serving from 12pm - 4pm", "€4.60", "https://newmansown.com/wp-content/uploads/2022/03/Homemade-lasagna-1200x675.png"),
//                    MenuItem(11,"non-vegan","Beef Stroganoff", "Serving from 12pm - 4pm", "€5.10", "https://images.sbs.com.au/dims4/default/d7695e0/2147483647/strip/true/crop/1200x675+0+825/resize/1280x720!/quality/90/?url=http%3A%2F%2Fsbs-au-brightspot.s3.amazonaws.com%2Fdrupal%2Ffood%2Fpublic%2F_mg_0627.jpg"),
//                    MenuItem(12,"non-vegan","Seafood Chowder", "Serving from 12pm - 4pm", "€4.80", "https://www.allrecipes.com/thmb/ahzAZgTaPoA7tAzt-VwmYH8sAR8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/81112_SeafoodChowder_ddmfs_2x1_gw_0470-f4d29eb212644c04949114798ff8e41c.jpg"),
//                    MenuItem(13,"non-vegan","Salmon And Asparagus", "Serving from 12pm - 4pm", "€4.60", "https://cdn.ruled.me/wp-content/uploads/2019/11/salmon-asparagus-and-hollandaise-featured.jpg"),
//                    MenuItem(14,"vegan","Vegetable Soup", "Serving from 12pm - 4pm", "€5.10", "https://thecozyapron.com/wp-content/uploads/2018/07/vegetable-soup_thecozyapron_1.jpg"),
//                    MenuItem(15,"vegan","Vegan Pizza", "Serving from 12pm - 4pm", "€4.50", "https://simplegreensmoothies.com/wp-content/uploads/vegan-pizza-recipe-dough-vegetarian-dairy-free-30.jpg"),
//                    MenuItem(16,"vegan","Veggie Burger", "Serving from 12pm - 4pm", "€5.70", "https://www.inspiredtaste.net/wp-content/uploads/2018/05/Homemade-Mushroom-Veggie-Burger-Recipe-1200.jpg"),
//                    MenuItem(17,"vegan","Vegan Pasta", "Serving from 12pm - 4pm", "€5.30", "https://www.foodandwine.com/thmb/9AqvuDWttUQIl_6G5MRv3-2sts0=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/green-pasta-FT-RECIPE0521-510d065cf45940fdb4e86b76000cfd80.jpg"),
//                    MenuItem(18,"vegan","Vegan Curry", "Serving from 12pm - 4pm", "€5.20", "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/slow-cooker-vegetable-curry-2b72ddd.jpg"),
//
//
//                    )
//                uploadMenuItemsToFirestore(allMenuItems)
            }
        }
    }

    fun uploadMenuItemsToFirestore(menuItems: List<MenuItem>) {
        val firestore = FirebaseFirestore.getInstance()
        val menuCollection = firestore.collection("menu_items")

        menuItems.forEach { item ->
            menuCollection.document(item.id.toString()) // Use the item ID as the document name
                .set(item)
                .addOnSuccessListener {
                    Log.d("Firestore", "Menu item ${item.name} uploaded successfully.")
                }
                .addOnFailureListener { e ->
                    Log.e("Firestore", "Error uploading ${item.name}", e)
                }
        }
    }
}