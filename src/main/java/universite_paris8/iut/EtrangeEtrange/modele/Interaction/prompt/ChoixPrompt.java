package universite_paris8.iut.EtrangeEtrange.modele.interaction.prompt;

public enum ChoixPrompt {
    MARCHANDER("Marchander"),
    PARLER("Parler"),
    SOIGNER("Soigner"),
    SUIVANT("Suivant"),
    OUI("Oui"),
    NON("Non");

    private final String displayName;

    ChoixPrompt(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ChoixPrompt fromString(String text) {
        for (ChoixPrompt choix : ChoixPrompt.values()) {
            if (choix.getDisplayName().equalsIgnoreCase(text)) {
                return choix;
            }
        }
        throw new IllegalArgumentException("Aucun choix correspondant : " + text);
    }
}
