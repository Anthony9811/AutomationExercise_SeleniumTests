package data;

import lombok.Getter;

@Getter
public enum Brands {
    POLO("Polo"),
    HM("H&M"),
    MADAME("Madame"),
    MAST_HARBOUR("Mast & Harbour"),
    BABYHUG("Babyhug"),
    ALLEN_SOLLY_JUNIOR("Allen Solly Junior"),
    KOOKIE_KIDS("Kookie Kids"),
    BIBA("Biba");

    /**
     * -- GETTER --
     *  Gets the exact string value of the brand name as it appears on the website.
     */
    private final String brandName;

    Brands(String brandName) {
        this.brandName = brandName;
    }

}
