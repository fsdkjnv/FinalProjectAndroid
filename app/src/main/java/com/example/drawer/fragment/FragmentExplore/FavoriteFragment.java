package com.example.drawer.fragment.FragmentExplore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawer.Adapter.AdapterExplore.ExploreAdapter;
import com.example.drawer.Data.DataExplore.ExploreItem;
import com.example.drawer.R;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    // Đây là một lớp Fragment dành cho màn hình "Yêu thích" trong ứng dụng Android của bạn.

    private List<ExploreItem> exploreItemList;
    private RecyclerView recyclerView;
    private ExploreAdapter exploreAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Phương thức này được gọi để tạo giao diện cho fragment.

        // LayoutInflater được sử dụng để bố trí (render) giao diện của fragment.
        // "inflater.inflate(R.layout.fragment_favorite, container, false);" bố trí (render) giao diện từ tệp layout fragment_favorite.xml.

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        exploreItemList = generateExploreItem();
        recyclerView = view.findViewById(R.id.recyclerViewExplore);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        exploreAdapter = new ExploreAdapter(getActivity(), exploreItemList);
        recyclerView.setAdapter(exploreAdapter);

        return view;
    }


    private List<ExploreItem> generateExploreItem()
    {
        List<ExploreItem> exploreItems = new ArrayList<>();
        exploreItems.add(new ExploreItem(R.drawable.flower_1,
                "Top 5 Gardening Trends for 2022",
                "In looking at what is trending in gardening in 2022, it’s clear that the pandemic has had a significant impact on how people are engaging with outdoor spaces at home. With lockdowns and social restrictions, these impacts have trickled down to the built environment and are affecting the way people design their gardens, what they’re planting, and even who has taken up gardening. According to the 2021 National Gardening Survey, 58% of 35-44 year-olds surveyed said they gardened more because of the pandemic. With the increased time most have spent at home, getting outdoors and in touch with the natural world by practicing mindfulness through growing has brought respite from the stress of the pandemic. Here are the top 5 gardening trends we expect to see in 2022.\n" +
                        "\n" +
                        "1. Small Spaces\n" +
                        "More people are getting into gardening, including those who live in small homes, condos, or apartments whose only access to the outdoors at home is a small courtyard space or balcony. According to the 2021 National Gardening Survey, 54% of respondents claim that they did more container gardening because of the pandemic and that they plan to continue doing so. This year we expect to see more paved spaces transformed into small outdoor havens, complete with floral and vines climbing fences and railings alongside containers full of edibles and blooms, like the Legion of Honor French Marigold Seeds shown here.\n" +
                        "\n" +
                        "2. Bring the Indoors Outside\n" +
                        "One of the hottest gardening trends of 2022 is creating more seamless transitions from indoor to outdoor spaces. For many, this starts with letting go of shortly clipped grass and trimmed yew bushes and using plants to soften the hard lines that sometimes divide home and lawn. Some are choosing complete indoor/outdoor design overhauls, incorporating a wall of windows or using the same flooring between connected indoor and outdoor spaces. However, there are plenty more subtle and less expensive options to attain the feeling of being embedded in nature right at home. Building an overhang on a patio space, such as a pergola, is a less expensive structural option that can be adorned with climbing plants, like the unique and striking Passion Flower vine. Adding comfortable seating in patio spaces surrounded by pots of flowering plants and tall grasses can make the home and yard feel more expansive and integrated.\n" +
                        "\n" +
                        "3. Incorporating Edibles\n" +
                        "Nearly everyone has felt the impacts of supply chain issues and inflation in recent days. It’s no surprise that growing your own food is gaining popularity in 2022. Lucy Bradley from NC State Extension’s Urban Horticulture program cites several reasons that growing food has increased in popularity.1 “First, people had more time at home. And with the initial supply chain challenges, they became interested in growing food,” says Bradley. “In fact, many seed companies ran out of seeds during the pandemic.” Incorporating edibles into the yard doesn’t have to mean tilling up a large chunk of space to dedicate to a vegetable garden. Many people are incorporating edibles into their ornamental landscaping to add visual interest including crops like Swiss Chard, kale, and vining veggies such as pole beans.\n" +
                        "\n" +
                        "4. Gardening for Wildlife\n" +
                        "An estimated 30.6 million U.S. adults say that they converted part of their lawn into a natural or wildflower landscape during the pandemic, a trend that is only expected to rise in 2022.2 As people become increasingly concerned about such things as pollinator populations and the fate of our planet, they’ve begun paying attention to their own hand in the matter. Native plantings are gaining in popularity among home gardeners as they can be a haven for bees, birds, butterflies, and other beneficial insects and animals. What plants will be popular in 2022? Regional Wildflower Mixes and State Mixes that are designed for your area and especially suited to your climate and the fauna/insects that inhabit it are quickly becoming all-the-rage.\n" +
                        "\n" +
                        "5. Gardening for Climate Change\n" +
                        "We would be remiss if we didn’t acknowledge the increased incidence of wildfires, record high temperatures, and treacherous winter conditions that have people feeling uneasy these last few years. Climate change not only impacts how we care for garden spaces, how we garden can impact our environment as well. Shifts in weather patterns affect everything from timing of plantings to watering needs in the garden. Selecting plants that are drought tolerant and particularly resilient to these swings can ensure that the garden thrives amidst these unknowns. Taking care when selecting and applying fertilizers and pesticides/herbicides can help keep groundwater clean and protect pollinators as can using sustainably produced and open-pollinated varieties. Eden Brothers has a wide selection of organic, non-GMO and heirloom varieties to choose from when planning your garden!\n" +
                        "\n" +
                        "[1] https://news.ncsu.edu/2021/07/pandemic-gardening-tips/\n" +
                        "\n" +
                        "[2] National Gardening Survey 2021 & National Wildlife Federation"
                , "Miss Vi"));
        exploreItems.add(new ExploreItem(R.drawable.flower_2, "What is Certified Organic?",
                "The health of our planet and our bodies is of the highest importance at Eden Brothers. That’s why we offer an extensive selection of Certified Organic seeds that meet all requirements set forth by the National Standard for Organic Agriculture. \n" +
                        "\n" +
                        "Stamp of Approval\n" +
                        "Per the USDA, in order to be Certified Organic, products must be meet certain criteria. They must be produced using agricultural production practices that:\n" +
                        "\n" +
                        "Foster resource cycling\n" +
                        "Promote ecological balance\n" +
                        "Maintain and improve soil and water quality\n" +
                        "Minimize the use of synthetic materials\n" +
                        "Conserve biodiversity\n" +
                        "Organic product labels must be reviewed and approved by a USDA-accredited certifying agent before being used in the marketplace. At Eden Brothers, our organic certification agency sends an inspector on a yearly basis to review our documentation, procedures and handling practices. We must pass this inspection to retain our organic certification, so we take great care in maintaining high standards on a daily basis.\n" +
                        "\n" +
                        "Higher Cost for Good Reason\n" +
                        "\n" +
                        "If you’ve shopped around, you probably noticed that organic products of all types (not just seeds) usually come with a heavier price tag. Why? Organic seeds are more expensive because the cost of production to meet all of the requirements is much higher. Organic crops are usually smaller than non-organic as well. These folks don’t receive the same benefits from the economies of scale that large farms see. Additionally, it costs significantly more to grow and process small amounts of organic food, as opposed to large quantities. But when you see the organic logo on any of our products, you’ll know that slightly higher price is for great reason.\n" +
                        "\n" +
                        "Organics at Eden Brothers\n" +
                        "As we become more knowledgeable and increasingly savvy concerning our health and the health of our planet, we make better choices of what to put into our bodies and plant on our land. Our large selection of organic vegetable seeds and organic herb seeds are for the discerning gardener who strives to make cleaner, safer choices of what to grow and put on their plate. Created for gardens of any size, we offer safe, natural, heirloom and open-pollinated organic seeds."
                ,"Mr Jane"));
        exploreItems.add(new ExploreItem(R.drawable.flower_3, "Summer 2021 Photo Contest",
                        "We’re lucky to have so many customers willing to share their gorgeous garden creations, whether flower, veggie or herb. They email us daily to show off what’s grown from their Eden Brothers’ seeds or bulbs. Knowing how talented our customers are and how photogenic our products can be, we decided to host a photo contest this summer.\n" +
                                "\n" +
                                "Our marketing team launched the Summer 2021 Photo Contest at the end of July thinking we’d get a hundred or so entries. By our August 16 deadline, we had over 500 photographs from across the country. And it came all the way down to these 3 winners who received an Eden Brothers’ gift certificate as their rewards.\n" +
                                "\n" +
                                "1st Place Winner: Lindsey Buerger | Sunflower Veggie Bowl\n" +
                                "\n" +
                                "2nd Place: Kristin Martina Turk | California Giant Sunset\n" +
                                "\n" +
                                "3rd Place: Scott Pennington | Coneflower\n" +
                                "Our Process\n" +
                                "The marketing team took the initial batch of 500+ and narrowed it down to 50 favorites to hand off to Eden Brothers’ horticulturist (who just so happens to also be a photographer). He vetted the images to make sure they contained our products, which was one of the participation requirements. He selected the top 20 that featured great composition, color, subject matter and that simply gave him the general feeling of “this is amazing.” It was not easy! So many dahlias, coneflowers, garden scapes, kids, pets and pollinators. But we persevered.\n" +
                                "\n" +
                                "Top 10 Photo Contest\n" +
                                "The Top 10\n" +
                                "After getting the finalists down to a Top Ten List, our Eden Brothers staff voted. We printed and posted each image in our warehouse with a high-class paper clip voting system: drop a clip in the envelope under your favorite photo. The most clips gets 1st place and so on.\n" +
                                "\n" +
                                "The Finalists\n" +
                                "We had such a hard time choosing the photo contest finalists and could not miss the opportunity to share them with you. This flip-book features our all-time favorites. We hope they inspire you as much as they did us! And keep an eye out for your own favorites in our marketing materials. The contributors all agreed that we could use their photos on our website, in emails, social media or any other marketing efforts. We’re grateful!\n" +
                                "\n" +
                                "View all the finalists here.\n" +
                                "Most Photographed Flowers\n" +
                                "We started to notice something as we scanned through hundreds of photos… people have similar ideas of what makes a beautiful image. For all you budding photographers, here’s a list of products that we saw over and over again. We encourage you to plant, grow and photograph your own!"
                        ,"Mr David"));

        exploreItems.add(new ExploreItem(R.drawable.flower_4, "Your Guide to Fall Vegetable Planting",
                        "As the vibrant hues of summer fade and a cool breeze begins to hint at the changing season, we as gardeners and plant enthusiasts eagerly anticipate the arrival of fall. While many may associate spring with planting, autumn presents a fantastic opportunity to continue cultivating your green space and enjoying a bountiful harvest. But let’s be honest, transitioning to a fall garden after all the work we’ve put in during the summer can be hard. Sometimes it’s nice to pack up the garden, so to speak, and work on something different. Also, in the higher zones, fall is often hotter or just as hot as summer, which prevents planting straight away after the school year begins. Nevertheless, fall vegetable planting, whenever that happens in your area, opens up a world of possibilities, allowing us to savor fresh produce even as the days grow shorter. What we plant in the fall is so different from what was growing during the summer, and our waning strength is often galvanized by that. In this blog, we’ll explore the joys and benefits of fall vegetable planting and provide you with some essential tips to make the most of this season.\n" +
                                "\n" +
                                "Embrace the Bounty (and Beauty) of Autumn\n" +
                                "Fall vegetable planting is more than just an extension of your gardening efforts; it’s a chance to embrace the unique beauty of the season. As leaves turn into brilliant shades of red, orange, and gold, your garden can become a canvas of both color and flavor.\n" +
                                "\n" +
                                "Imagine strolling through a garden dotted with leafy kale, vibrant Swiss chard, and hearty Brussels sprouts against the backdrop of falling leaves. It’s a picturesque scene that blends the aesthetic appeal of autumn with the rewards of homegrown produce.\n" +
                                "\n" +
                                "Advantages of Fall Vegetable Planting\n" +
                                "Cooler Temperatures: Fall’s cooler temperatures provide a more favorable environment for certain vegetables. Plants that struggle in the summer heat can thrive during the milder days of autumn.\n" +
                                "Less Pests and Disease: Many common garden pests and diseases tend to diminish as summer ends. This reduces the likelihood of your crops being compromised and often leads to healthier plants.\n" +
                                "Extended Harvest: By planning your fall garden carefully, you can enjoy a prolonged harvest season. Some crops even develop sweeter flavors as they experience cooler temperatures.\n" +
                                "Less Watering: Fall tends to bring more rain, reducing the need for excessive watering. This is not only environmentally friendly but also helps streamline your gardening routine.\n" +
                                "A Good Time to Grow Cover Crops: Crops like kale, radishes, turnips, and other broad-leaf greens are ideal for use as cover crops and food sources during the winter. Clover, ryegrass, legumes, and peas are also good cover crops for winter.\n" +
                                "\n" +
                                "A Chance to Practice Crop Rotation: Some plants contribute to soil health. For example, peas and nasturtiums add nitrogen to soil. By planting these in the fall, your soil is then ready for nitrogen-taking crops like corn the next summer.\n" +
                                "\n" +
                                "Top Picks for Fall Planting\n" +
                                "Broccoli: This cool-weather favorite develops tender florets during fall’s mild temperatures. If you are not a fan of broccoli, we urge you to try growing it yourself. As we experience with most home-grown food, it is more tender and more flavorful than any mass produced, store bought broccoli out there.\n" +
                                "\n" +
                                "Carrots: Cooler weather enhances the sweetness of carrots, making them a delightful addition to fall dishes. Nothing beats baked/broiled home-grown carrots with a bit of olive oil and sea salt. So sweet, so tender, you will eat them right off the sheet!\n" +
                                "\n" +
                                "Kale: A nutritional powerhouse, kale becomes even more robust and flavorful in cooler climates. Add kale to your fall and winter soups, or toss it with olive oil and salt and bake for a crunchy, super healthy snack.\n" +
                                "\n" +
                                "Lettuce & Arugula: With a variety of types available, lettuce is perfect for fall salads and sandwiches. You may not be in the mood for a cold salad on a cold day, but consider adding fresh leaves to a grilled panini.\n" +
                                "\n" +
                                "Spinach: Its cold tolerance makes spinach an excellent candidate for fall planting. The perfect addition to fall soups!\n" +
                                "\n" +
                                "Radishes: These quick-growing root vegetables are ready to harvest in just a few weeks, making them a convenient choice for fall gardening. We have always loved how satisfying growing radishes is – days to maturity is under a month.\n" +
                                "\n" +
                                "Tips for Successful Fall Gardening\n" +
                                "Know Your First Frost Date: Research the average date of the first frost in your region. This information is crucial for planning when to plant and which crops to choose.\n" +
                                "\n" +
                                "Choose Shorter Days to Maturity: Opt for vegetable varieties with shorter days to maturity to ensure they can reach full growth before frost arrives.\n" +
                                "\n" +
                                "Prepare the Soil: Clear your garden beds of summer debris, amend the soil with compost, and ensure proper drainage for healthy root development.\n" +
                                "\n" +
                                "Provide Protection: Be prepared to cover your crops with frost cloths or row covers if a surprise frost threatens.\n" +
                                "\n" +
                                "Water Wisely: While fall ofte",
                "Mr Kane"));

        exploreItems.add(new ExploreItem(R.drawable.flower_5, "Pumpkin Pollination",
                "The story isn’t a new one – Your pumpkin vine is truly magnificent, boasting a robust and healthy appearance with its lush, deep green leaves and abundant blossoms. Big, gorgeous yellow blooms open and close, but day after day you realize something—the absence of any signs of fruit. What is wrong? Tomatoes and beans are forming in your summer garden, but your pumpkin vine is big and beautiful… and lonely, with no young pumpkins forming. Do you need to intervene? Won’t nature take care of this? You may be wondering whether pumpkins possess the ability to self-pollinate or if your intervention is required. If manual pollination is necessary, how do you do it?\n" +
                        "\n" +
                        "Learn from our gardening friend Linda and her pumpkinless season, and you’ll see that with a little help, you will have beautiful pumpkins come fall. Whenever we see young fruits forming, our gardening hearts swell with pride. When those same fruits wither and die, we are left perplexed, confused, and let’s face it, angry! Before succumbing to panic over the death of fruit, let’s delve into the intriguing realm of pumpkin plant pollination.\n" +
                        "\n" +
                        "Male and Female Flowers\n" +
                        "First and foremost, it’s essential to understand that pumpkins, much like other cucurbits, possess distinct male and female flowers on a single plant. Consequently, fruit formation necessitates the cooperation of both genders. The transfer of pollen from the male flower to the female flower is indispensable for this process to occur.\n" +
                        "\n" +
                        "Male pumpkin flower\n" +
                        "Female pumpkin flower\n" +
                        "Male flowers bloom on long stems and usually reach high above the leaves. These live on the plant for a single day before wilting, but don’t fret, the vine will produce many more male flowers than it does female flowers. Remain calm. Within approximately a week, the female flowers will grace the plant with their presence, staying low to the vine with a premature fruit beneath it. The male blooms will continue to make their appearance as well.\n" +
                        "Young male flower on tall stem\n" +
                        "Young female flower (bulbous area is the ovary)\n" +
                        "Are Pumpkins Capable of Self Pollination?\n" +
                        "In short, the answer is no. Pumpkins rely on bees or, in some cases, human intervention for successful pollination. The male flowers of pumpkins produce both nectar and pollen, while the female flowers contain a higher quantity of nectar but no pollen. Bees play a crucial role in the pollination process by visiting the male flowers, where the sticky pollen granules adhere to their bodies. They then proceed to the nectar-rich female flowers, facilitating the transfer of pollen. This intricate dance between bees and flowers enhances the quality of the resulting fruit through increased pollinator activity.\n" +
                        "\n" +
                        "Despite the presence of both male and female flowers, you may encounter difficulties with pumpkin plant pollination for various reasons. Factors such as the use of broad-spectrum pesticides in the vicinity or unfavorable weather conditions such as excessive rain or heat may impede bee activity. In such cases, manual hand-pollination of pumpkins becomes a potential necessity.\n" +
                        "\n" +
                        "In Linda’s garden, she experienced a summer when none of her female pumpkin blossoms were pollinated, resulting in, you guessed it, no pumpkins. Surely there were enough bees in the area to let nature do its thing, but alas, come Halloween, she was pumpkinless. From now on, when she visits her garden in the early morning hours and sees both male and female blossoms open, she intervenes by hand pollinating. She see the bees and knows they will do their work, but she helps out a little…just…in…case. Because there is nothing sadder than having a summer garden filled with pumpkin leaves, yet having to purchase a pumpkin from someone else’s garden for the holidays.\n" +
                        "\n" +
                        "How to Hand Pollinate\n" +
                        "Before embarking on the task of hand-pollinating your pumpkin plant, it is crucial to distinguish between the female and male blooms. To identify a female flower, examine the point where the stem connects with the flower. You will notice a small structure resembling a miniature fruit. This is the ovary, which indicates a female bloom. On the other hand, male flowers tend to be on stems stretched taller in comparison, devoid of immature fruit, and often appear in clusters.\n" +
                        "\n" +
                        "Male flower with pollen\n" +
                        "\n" +
                        "Female flower waiting to be pollinated\n" +
                        "\n" +
                        "Pollinated female flower. After pollination the young fruit continues to grow.\n" +
                        "There are two straightforward methods for hand-pollinating pumpkins. You can choose either a small, delicate paintbrush or a cotton swab to perform the task. Begin by gently touching the anther located at the center of the male flower. The swab or brush will collect pollen during this process. Next, carefully transfer the collected pollen by touching the swab or brush to the stigma at the center of the female flower. This simple action facilitates the pollination process and encourages fruit development.  Another approach to hand-pollination involves removing the male flower and gently shaking"
                ,"Mr Tuna"));


        exploreItems.add(new ExploreItem(R.drawable.flower_6, "DIY Garden Trellis – So Easy!",
                "One of the easiest and rewarding gardening projects that anyone can create is a trellis. You don’t need to be a handyman or a skilled crafter for this one! Sure, trellises are wonderful features for gardeners with small growing areas, but they are much more than that. So many veggies and flowers are climbers and thrive when they have room to grow upward towards the sun and can hang onto an object as they reach higher and higher. Whether you have acres of property or just a little plot of land, an easy way to maximize your growing space is to grow vertically.\n" +
                        "\n" +
                        "Plan it Out\n" +
                        "\n" +
                        "Follow the steps below to build a trellis and add extra growing area to your garden. We added a cucumber trellis to a raised bed, but you can also put stakes directly into the ground.\u2060 Most gardeners probably have many of the materials required to build a trellis, but if not your local garden center or hardware store will provide all you need. There aren’t many garden projects as inexpensive as a trellis!\n" +
                        "\n" +
                        "Before you get started, it’s a good idea to gather all of your materials and lay them out in an accessible location.\n" +
                        "\n" +
                        "\u2060Materials Needed\n" +
                        "Two garden stakes\u2060\n" +
                        "Chicken wire\u2060\n" +
                        "Screws\n" +
                        "Screwdriver or drill \u2060\n" +
                        "Zip-ties\u2060\n" +
                        "Scissors\u2060 \u2060\n" +
                        "Get Started\n" +
                        "Step One: Attach the garden stakes to the inside of your garden bed for extra support. \u2060\n" +
                        "\n" +
                        "Securing the garden stakes.\n" +
                        "Step Two: Stretch your chicken wire between your stakes.\u2060\n" +
                        "\n" +
                        "Step Three: Attach the wire with zip-ties and cut off the excess.\u2060 \u2060\n" +
                        "\n" +
                        "Attaching the chicken wire.\n" +
                        "\n" +
                        "Clipping the zip ties.\n" +
                        "Step Four: Plant your seedlings along the base of the trellis and watch them grow!\n" +
                        "\n" +
                        "Delicious cucumber hanging on tight!\n" +
                        "This easy and inexpensive trellis is perfect for so many delicious veggies and lovely flowers. Try these:\n" +
                        "\n" +
                        "Cucumbers\n" +
                        "Cherry tomatoes\n" +
                        "Vining flowers\n" +
                        "Beans\n" +
                        "Peas\n" +
                        "Okra, and more!\n" +
                        "\n" +
                        "The final product!\n" +
                        "\n" +
                        "Cucumbers are perfect for the trellis.\n" +
                        "Want More?\n" +
                        "For additional gardening tutorials and to watch a demo about how to build this trellis:\n" +
                        "\n" +
                        "Follow Eden Brothers on TikTok.\n" +
                        "\n" +
                        "Follow Eden Brothers on YouTube.\n" +
                        "\n" +
                        "Watch the trellis demonstration."
                ,"Mr Mary"));


        return exploreItems;
    }
}
