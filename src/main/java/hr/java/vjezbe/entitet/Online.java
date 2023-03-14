package hr.java.vjezbe.entitet;

sealed interface Online permits Ispit {
    public void setSoftware(String softwareName);
}
