package io.realm;


public interface com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface {
    public String realmGet$id();
    public void realmSet$id(String value);
    public String realmGet$nombre();
    public void realmSet$nombre(String value);
    public String realmGet$tipo();
    public void realmSet$tipo(String value);
    public String realmGet$fecha();
    public void realmSet$fecha(String value);
    public String realmGet$latitud();
    public void realmSet$latitud(String value);
    public String realmGet$longitud();
    public void realmSet$longitud(String value);
    public int realmGet$valoracion();
    public void realmSet$valoracion(int value);
    public boolean realmGet$favorito();
    public void realmSet$favorito(boolean value);
    public String realmGet$usuarioID();
    public void realmSet$usuarioID(String value);
    public RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> realmGet$fotos();
    public void realmSet$fotos(RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> value);
}
