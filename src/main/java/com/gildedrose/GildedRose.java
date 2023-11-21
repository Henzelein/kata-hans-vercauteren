package com.gildedrose;

class GildedRose {

    private final String AGED_BRIE = "Aged Brie";
    private final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private Item[] items;

    public GildedRose( Item[] items ) {
        this.items = items;
    }

    public void updateQuality() {
        for( int i = 0; i < items.length; i++ ) {
            Item item = items[i];
            if ( item.name.equals( AGED_BRIE ) ) {
                updateAgedBrie( item );
                item.sellIn--;
            } else if ( item.name.equals( BACKSTAGE_PASSES ) ) {
                updateBackstagePasses( item );
                item.sellIn--;
            } else if ( item.name.equals( SULFURAS ) ) {
                // do nothing
            } else if ( item.name.startsWith( "Conjured" ) ) {
                // Conjured items should decrease in quality twice as fast as normal items
                updateItem( item );
                updateItem( item );
                item.sellIn--;
            } else {
                updateItem( item );
                item.sellIn--;
            }
        }
    }

    public void updateAgedBrie(Item agedBrie) {
        agedBrie.quality++;
        // If the sellIn date is passed, the quality decreases twice as fast
        // Since aged Brie increases in quality, it also increases twice as fast (existing  functionality)
        if ( agedBrie.sellIn <= 0 )
            agedBrie.quality++;
        agedBrie.quality = Math.min(50, agedBrie.quality);
    }

    public void updateBackstagePasses(Item backstagePasses) {
        if (backstagePasses.sellIn <= 0)
            backstagePasses.quality = 0;
        else if (backstagePasses.sellIn <= 5)
            backstagePasses.quality += 3;
        else if (backstagePasses.sellIn <= 10)
            backstagePasses.quality += 2;
        else
            backstagePasses.quality += 1;

        // The quality of an item can never be greater than 50
        backstagePasses.quality = Math.min( 50, backstagePasses.quality );
    }

    public void updateItem( Item item ) {
        if ( item.sellIn <= 0 ) {
            item.quality -= 2;
        } else {
            item.quality -= 1;
        }
        item.quality = Math.max( item.quality, 0 );
    }

    public Item[] getItems() {
        return items;
    }

}
