public class Equipment {
    private String item;
    private boolean isTwoHanded;
    private boolean isEquipped;
    private String equipType;
    private Player player;
    private int power;

    public Equipment(String item, boolean isTwoHanded, boolean isEquipped, String equipType, Player player, int power) {
        this.item = item;
        this.isTwoHanded = isTwoHanded;
        this.isEquipped = isEquipped;
        this.equipType = equipType;
        this.player = player;
        this.power = power;
    }

    public void loadEquipedItem() {
        if(this.isEquipped) {
            switch (equipType) {
                case "Heavy Wep":
                    this.player.setAp(player.getAp() + power*2);
                    this.player.setSpeed((int)(player.getSpeed() / 1.3));
                    break;
                case "Heavy Arm":
                    this.player.setDp(player.getDp() + power*2);
                    this.player.setSpeed((int)(player.getSpeed() / 2));

            }
        }
    }
}