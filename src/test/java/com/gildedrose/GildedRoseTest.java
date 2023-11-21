package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private final String AGED_BRIE = "Aged Brie";
    private final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private final String CONJURED = "Conjured Mana Cake";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";

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
        Item[] items = new Item[]{new Item( SULFURAS, 10, 80 )};
        GildedRose app = new GildedRose( items );
        app.updateQuality();
        assertEquals( 80, app.items[0].quality );
        assertEquals( 10, app.items[0].sellIn );
    }

    @Test
    void backstagePassesTest() {
        Item[] items = new Item[]{new Item( BACKSTAGE_PASSES, 10, 30 ),
                new Item( BACKSTAGE_PASSES, 5, 30 ),
                new Item( BACKSTAGE_PASSES, 0, 30 ),
                new Item( BACKSTAGE_PASSES, 20, 30 )};
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
        Item[] items = new Item[]{new Item( AGED_BRIE, 10, 30 ),
                new Item( AGED_BRIE, 10, 50 ),
                new Item( AGED_BRIE, -10, 30 )
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
                new Item( CONJURED, 10, 30 ),
                new Item( CONJURED, 0, 30 ),
                new Item( CONJURED, 5, 0 )};
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