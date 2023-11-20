package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void happyPathTest() {
        Item[] items = new Item[]{new Item( "foo", 10, 20 ),
                new Item( "old", -1, 20 ),
                new Item( "min", 5, 0 )};
        GildedRose app = new GildedRose( items );
        app.updateQuality();
        assertEquals( "foo", app.items[0].name );
        assertEquals( 19, app.items[0].quality );
        assertEquals( 18, app.items[1].quality );
        assertEquals( 0, app.items[2].quality );
        assertEquals( 9, app.items[0].sellIn );
        assertEquals( -2, app.items[1].sellIn );
        assertEquals( 4, app.items[2].sellIn );
    }

    @Test
    void sulfurasTest() {
        Item[] items = new Item[]{new Item( "Sulfuras, Hand of Ragnaros", 10, 80 )};
        GildedRose app = new GildedRose( items );
        app.updateQuality();
        assertEquals( 80, app.items[0].quality );
        assertEquals( 10, app.items[0].sellIn );
    }

    @Test
    void backstagePassesTest() {
        Item[] items = new Item[]{new Item( "Backstage passes to a TAFKAL80ETC concert", 10, 30 ),
                new Item( "Backstage passes to a TAFKAL80ETC concert", 5, 30 ),
                new Item( "Backstage passes to a TAFKAL80ETC concert", 0, 30 ),
                new Item( "Backstage passes to a TAFKAL80ETC concert", 20, 30 )};
        GildedRose app = new GildedRose( items );
        app.updateQuality();
        assertEquals( 32, app.items[0].quality );
        assertEquals( 33, app.items[1].quality );
        assertEquals( 0, app.items[2].quality );
        assertEquals( 31, app.items[3].quality );
        assertEquals( 9, app.items[0].sellIn );
        assertEquals( 4, app.items[1].sellIn );
        assertEquals( -1, app.items[2].sellIn );
        assertEquals( 19, app.items[3].sellIn );
    }

    @Test
    void agedBrieTest() {
        Item[] items = new Item[]{new Item( "Aged Brie", 10, 30 ),
                new Item( "Aged Brie", 10, 50 ),
                new Item( "Aged Brie", -10, 30 )
        };
        GildedRose app = new GildedRose( items );
        app.updateQuality();
        assertEquals( 31, app.items[0].quality );
        assertEquals( 50, app.items[1].quality );
        assertEquals( 32, app.items[2].quality );
        assertEquals( 9, app.items[0].sellIn );
        assertEquals( 9, app.items[1].sellIn );
        assertEquals( -11, app.items[2].sellIn );
    }

    @Test
    void conjuredItemTest() {
        Item[] items = new Item[]{
                new Item( "Conjured Mana Cake", 10, 30 ),
                new Item( "Conjured Mana Cake", 0, 30 ),
                new Item( "Conjured Mana Cake", 5, 0 )};
        GildedRose app = new GildedRose( items );
        app.updateQuality();
        assertEquals( 28, app.items[0].quality );
        assertEquals( 26, app.items[1].quality );
        assertEquals( 0, app.items[2].quality );
        assertEquals( 9, app.items[0].sellIn );
        assertEquals( -1, app.items[1].sellIn );
        assertEquals( 4, app.items[2].sellIn );

    }
}