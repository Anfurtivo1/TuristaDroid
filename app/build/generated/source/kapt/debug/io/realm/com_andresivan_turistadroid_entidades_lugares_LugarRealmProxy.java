package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ImportFlag;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy extends com.andresivan.turistadroid.entidades.lugares.Lugar
    implements RealmObjectProxy, com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface {

    static final class LugarColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long idIndex;
        long nombreIndex;
        long tipoIndex;
        long fechaIndex;
        long latitudIndex;
        long longitudIndex;
        long valoracionIndex;
        long favoritoIndex;
        long usuarioIDIndex;
        long fotosIndex;

        LugarColumnInfo(OsSchemaInfo schemaInfo) {
            super(10);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Lugar");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.nombreIndex = addColumnDetails("nombre", "nombre", objectSchemaInfo);
            this.tipoIndex = addColumnDetails("tipo", "tipo", objectSchemaInfo);
            this.fechaIndex = addColumnDetails("fecha", "fecha", objectSchemaInfo);
            this.latitudIndex = addColumnDetails("latitud", "latitud", objectSchemaInfo);
            this.longitudIndex = addColumnDetails("longitud", "longitud", objectSchemaInfo);
            this.valoracionIndex = addColumnDetails("valoracion", "valoracion", objectSchemaInfo);
            this.favoritoIndex = addColumnDetails("favorito", "favorito", objectSchemaInfo);
            this.usuarioIDIndex = addColumnDetails("usuarioID", "usuarioID", objectSchemaInfo);
            this.fotosIndex = addColumnDetails("fotos", "fotos", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        LugarColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new LugarColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final LugarColumnInfo src = (LugarColumnInfo) rawSrc;
            final LugarColumnInfo dst = (LugarColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.nombreIndex = src.nombreIndex;
            dst.tipoIndex = src.tipoIndex;
            dst.fechaIndex = src.fechaIndex;
            dst.latitudIndex = src.latitudIndex;
            dst.longitudIndex = src.longitudIndex;
            dst.valoracionIndex = src.valoracionIndex;
            dst.favoritoIndex = src.favoritoIndex;
            dst.usuarioIDIndex = src.usuarioIDIndex;
            dst.fotosIndex = src.fotosIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private LugarColumnInfo columnInfo;
    private ProxyState<com.andresivan.turistadroid.entidades.lugares.Lugar> proxyState;
    private RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotosRealmList;

    com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (LugarColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.andresivan.turistadroid.entidades.lugares.Lugar>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$nombre() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nombreIndex);
    }

    @Override
    public void realmSet$nombre(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'nombre' to null.");
            }
            row.getTable().setString(columnInfo.nombreIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'nombre' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.nombreIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$tipo() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.tipoIndex);
    }

    @Override
    public void realmSet$tipo(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'tipo' to null.");
            }
            row.getTable().setString(columnInfo.tipoIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'tipo' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.tipoIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$fecha() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.fechaIndex);
    }

    @Override
    public void realmSet$fecha(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'fecha' to null.");
            }
            row.getTable().setString(columnInfo.fechaIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'fecha' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.fechaIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$latitud() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.latitudIndex);
    }

    @Override
    public void realmSet$latitud(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'latitud' to null.");
            }
            row.getTable().setString(columnInfo.latitudIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'latitud' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.latitudIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$longitud() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.longitudIndex);
    }

    @Override
    public void realmSet$longitud(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'longitud' to null.");
            }
            row.getTable().setString(columnInfo.longitudIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'longitud' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.longitudIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$valoracion() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.valoracionIndex);
    }

    @Override
    public void realmSet$valoracion(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.valoracionIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.valoracionIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public boolean realmGet$favorito() {
        proxyState.getRealm$realm().checkIfValid();
        return (boolean) proxyState.getRow$realm().getBoolean(columnInfo.favoritoIndex);
    }

    @Override
    public void realmSet$favorito(boolean value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setBoolean(columnInfo.favoritoIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setBoolean(columnInfo.favoritoIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$usuarioID() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.usuarioIDIndex);
    }

    @Override
    public void realmSet$usuarioID(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'usuarioID' to null.");
            }
            row.getTable().setString(columnInfo.usuarioIDIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'usuarioID' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.usuarioIDIndex, value);
    }

    @Override
    public RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> realmGet$fotos() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (fotosRealmList != null) {
            return fotosRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.fotosIndex);
            fotosRealmList = new RealmList<com.andresivan.turistadroid.entidades.fotos.Foto>(com.andresivan.turistadroid.entidades.fotos.Foto.class, osList, proxyState.getRealm$realm());
            return fotosRealmList;
        }
    }

    @Override
    public void realmSet$fotos(RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("fotos")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> original = value;
                value = new RealmList<com.andresivan.turistadroid.entidades.fotos.Foto>();
                for (com.andresivan.turistadroid.entidades.fotos.Foto item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.fotosIndex);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.andresivan.turistadroid.entidades.fotos.Foto linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.setRow(i, ((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else {
            osList.removeAll();
            if (value == null) {
                return;
            }
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.andresivan.turistadroid.entidades.fotos.Foto linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Lugar", 10, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("nombre", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("tipo", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("fecha", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("latitud", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("longitud", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("valoracion", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("favorito", RealmFieldType.BOOLEAN, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("usuarioID", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedLinkProperty("fotos", RealmFieldType.LIST, "Foto");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static LugarColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new LugarColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Lugar";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Lugar";
    }

    @SuppressWarnings("cast")
    public static com.andresivan.turistadroid.entidades.lugares.Lugar createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.andresivan.turistadroid.entidades.lugares.Lugar obj = null;
        if (update) {
            Table table = realm.getTable(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
            LugarColumnInfo columnInfo = (LugarColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.lugares.Lugar.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("fotos")) {
                excludeFields.add("fotos");
            }
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy) realm.createObjectInternal(com.andresivan.turistadroid.entidades.lugares.Lugar.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy) realm.createObjectInternal(com.andresivan.turistadroid.entidades.lugares.Lugar.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface objProxy = (com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) obj;
        if (json.has("nombre")) {
            if (json.isNull("nombre")) {
                objProxy.realmSet$nombre(null);
            } else {
                objProxy.realmSet$nombre((String) json.getString("nombre"));
            }
        }
        if (json.has("tipo")) {
            if (json.isNull("tipo")) {
                objProxy.realmSet$tipo(null);
            } else {
                objProxy.realmSet$tipo((String) json.getString("tipo"));
            }
        }
        if (json.has("fecha")) {
            if (json.isNull("fecha")) {
                objProxy.realmSet$fecha(null);
            } else {
                objProxy.realmSet$fecha((String) json.getString("fecha"));
            }
        }
        if (json.has("latitud")) {
            if (json.isNull("latitud")) {
                objProxy.realmSet$latitud(null);
            } else {
                objProxy.realmSet$latitud((String) json.getString("latitud"));
            }
        }
        if (json.has("longitud")) {
            if (json.isNull("longitud")) {
                objProxy.realmSet$longitud(null);
            } else {
                objProxy.realmSet$longitud((String) json.getString("longitud"));
            }
        }
        if (json.has("valoracion")) {
            if (json.isNull("valoracion")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'valoracion' to null.");
            } else {
                objProxy.realmSet$valoracion((int) json.getInt("valoracion"));
            }
        }
        if (json.has("favorito")) {
            if (json.isNull("favorito")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'favorito' to null.");
            } else {
                objProxy.realmSet$favorito((boolean) json.getBoolean("favorito"));
            }
        }
        if (json.has("usuarioID")) {
            if (json.isNull("usuarioID")) {
                objProxy.realmSet$usuarioID(null);
            } else {
                objProxy.realmSet$usuarioID((String) json.getString("usuarioID"));
            }
        }
        if (json.has("fotos")) {
            if (json.isNull("fotos")) {
                objProxy.realmSet$fotos(null);
            } else {
                objProxy.realmGet$fotos().clear();
                JSONArray array = json.getJSONArray("fotos");
                for (int i = 0; i < array.length(); i++) {
                    com.andresivan.turistadroid.entidades.fotos.Foto item = com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$fotos().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.andresivan.turistadroid.entidades.lugares.Lugar createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.andresivan.turistadroid.entidades.lugares.Lugar obj = new com.andresivan.turistadroid.entidades.lugares.Lugar();
        final com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface objProxy = (com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("nombre")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$nombre((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$nombre(null);
                }
            } else if (name.equals("tipo")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tipo((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tipo(null);
                }
            } else if (name.equals("fecha")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$fecha((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$fecha(null);
                }
            } else if (name.equals("latitud")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$latitud((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$latitud(null);
                }
            } else if (name.equals("longitud")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$longitud((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$longitud(null);
                }
            } else if (name.equals("valoracion")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$valoracion((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'valoracion' to null.");
                }
            } else if (name.equals("favorito")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$favorito((boolean) reader.nextBoolean());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'favorito' to null.");
                }
            } else if (name.equals("usuarioID")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$usuarioID((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$usuarioID(null);
                }
            } else if (name.equals("fotos")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$fotos(null);
                } else {
                    objProxy.realmSet$fotos(new RealmList<com.andresivan.turistadroid.entidades.fotos.Foto>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.andresivan.turistadroid.entidades.fotos.Foto item = com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$fotos().add(item);
                    }
                    reader.endArray();
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        return realm.copyToRealm(obj);
    }

    private static com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.lugares.Lugar.class), false, Collections.<String>emptyList());
        io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy obj = new io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.andresivan.turistadroid.entidades.lugares.Lugar copyOrUpdate(Realm realm, LugarColumnInfo columnInfo, com.andresivan.turistadroid.entidades.lugares.Lugar object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.andresivan.turistadroid.entidades.lugares.Lugar) cachedRealmObject;
        }

        com.andresivan.turistadroid.entidades.lugares.Lugar realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstString(pkColumnIndex, ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.andresivan.turistadroid.entidades.lugares.Lugar copy(Realm realm, LugarColumnInfo columnInfo, com.andresivan.turistadroid.entidades.lugares.Lugar newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.andresivan.turistadroid.entidades.lugares.Lugar) cachedRealmObject;
        }

        com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface realmObjectSource = (com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) newObject;

        Table table = realm.getTable(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.nombreIndex, realmObjectSource.realmGet$nombre());
        builder.addString(columnInfo.tipoIndex, realmObjectSource.realmGet$tipo());
        builder.addString(columnInfo.fechaIndex, realmObjectSource.realmGet$fecha());
        builder.addString(columnInfo.latitudIndex, realmObjectSource.realmGet$latitud());
        builder.addString(columnInfo.longitudIndex, realmObjectSource.realmGet$longitud());
        builder.addInteger(columnInfo.valoracionIndex, realmObjectSource.realmGet$valoracion());
        builder.addBoolean(columnInfo.favoritoIndex, realmObjectSource.realmGet$favorito());
        builder.addString(columnInfo.usuarioIDIndex, realmObjectSource.realmGet$usuarioID());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        // Finally add all fields that reference other Realm Objects, either directly or through a list
        RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotosList = realmObjectSource.realmGet$fotos();
        if (fotosList != null) {
            RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotosRealmList = realmObjectCopy.realmGet$fotos();
            fotosRealmList.clear();
            for (int i = 0; i < fotosList.size(); i++) {
                com.andresivan.turistadroid.entidades.fotos.Foto fotosItem = fotosList.get(i);
                com.andresivan.turistadroid.entidades.fotos.Foto cachefotos = (com.andresivan.turistadroid.entidades.fotos.Foto) cache.get(fotosItem);
                if (cachefotos != null) {
                    fotosRealmList.add(cachefotos);
                } else {
                    fotosRealmList.add(com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.copyOrUpdate(realm, (com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.FotoColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.fotos.Foto.class), fotosItem, update, cache, flags));
                }
            }
        }

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.andresivan.turistadroid.entidades.lugares.Lugar object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
        long tableNativePtr = table.getNativePtr();
        LugarColumnInfo columnInfo = (LugarColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$nombre = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$nombre();
        if (realmGet$nombre != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nombreIndex, rowIndex, realmGet$nombre, false);
        }
        String realmGet$tipo = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$tipo();
        if (realmGet$tipo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tipoIndex, rowIndex, realmGet$tipo, false);
        }
        String realmGet$fecha = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$fecha();
        if (realmGet$fecha != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fechaIndex, rowIndex, realmGet$fecha, false);
        }
        String realmGet$latitud = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$latitud();
        if (realmGet$latitud != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.latitudIndex, rowIndex, realmGet$latitud, false);
        }
        String realmGet$longitud = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$longitud();
        if (realmGet$longitud != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.longitudIndex, rowIndex, realmGet$longitud, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.valoracionIndex, rowIndex, ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$valoracion(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.favoritoIndex, rowIndex, ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$favorito(), false);
        String realmGet$usuarioID = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$usuarioID();
        if (realmGet$usuarioID != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.usuarioIDIndex, rowIndex, realmGet$usuarioID, false);
        }

        RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotosList = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$fotos();
        if (fotosList != null) {
            OsList fotosOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.fotosIndex);
            for (com.andresivan.turistadroid.entidades.fotos.Foto fotosItem : fotosList) {
                Long cacheItemIndexfotos = cache.get(fotosItem);
                if (cacheItemIndexfotos == null) {
                    cacheItemIndexfotos = com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insert(realm, fotosItem, cache);
                }
                fotosOsList.addRow(cacheItemIndexfotos);
            }
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
        long tableNativePtr = table.getNativePtr();
        LugarColumnInfo columnInfo = (LugarColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.andresivan.turistadroid.entidades.lugares.Lugar object = null;
        while (objects.hasNext()) {
            object = (com.andresivan.turistadroid.entidades.lugares.Lugar) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$nombre = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$nombre();
            if (realmGet$nombre != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nombreIndex, rowIndex, realmGet$nombre, false);
            }
            String realmGet$tipo = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$tipo();
            if (realmGet$tipo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.tipoIndex, rowIndex, realmGet$tipo, false);
            }
            String realmGet$fecha = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$fecha();
            if (realmGet$fecha != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.fechaIndex, rowIndex, realmGet$fecha, false);
            }
            String realmGet$latitud = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$latitud();
            if (realmGet$latitud != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.latitudIndex, rowIndex, realmGet$latitud, false);
            }
            String realmGet$longitud = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$longitud();
            if (realmGet$longitud != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.longitudIndex, rowIndex, realmGet$longitud, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.valoracionIndex, rowIndex, ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$valoracion(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.favoritoIndex, rowIndex, ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$favorito(), false);
            String realmGet$usuarioID = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$usuarioID();
            if (realmGet$usuarioID != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.usuarioIDIndex, rowIndex, realmGet$usuarioID, false);
            }

            RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotosList = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$fotos();
            if (fotosList != null) {
                OsList fotosOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.fotosIndex);
                for (com.andresivan.turistadroid.entidades.fotos.Foto fotosItem : fotosList) {
                    Long cacheItemIndexfotos = cache.get(fotosItem);
                    if (cacheItemIndexfotos == null) {
                        cacheItemIndexfotos = com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insert(realm, fotosItem, cache);
                    }
                    fotosOsList.addRow(cacheItemIndexfotos);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.andresivan.turistadroid.entidades.lugares.Lugar object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
        long tableNativePtr = table.getNativePtr();
        LugarColumnInfo columnInfo = (LugarColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$nombre = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$nombre();
        if (realmGet$nombre != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nombreIndex, rowIndex, realmGet$nombre, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nombreIndex, rowIndex, false);
        }
        String realmGet$tipo = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$tipo();
        if (realmGet$tipo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tipoIndex, rowIndex, realmGet$tipo, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tipoIndex, rowIndex, false);
        }
        String realmGet$fecha = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$fecha();
        if (realmGet$fecha != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fechaIndex, rowIndex, realmGet$fecha, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fechaIndex, rowIndex, false);
        }
        String realmGet$latitud = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$latitud();
        if (realmGet$latitud != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.latitudIndex, rowIndex, realmGet$latitud, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.latitudIndex, rowIndex, false);
        }
        String realmGet$longitud = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$longitud();
        if (realmGet$longitud != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.longitudIndex, rowIndex, realmGet$longitud, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.longitudIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.valoracionIndex, rowIndex, ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$valoracion(), false);
        Table.nativeSetBoolean(tableNativePtr, columnInfo.favoritoIndex, rowIndex, ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$favorito(), false);
        String realmGet$usuarioID = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$usuarioID();
        if (realmGet$usuarioID != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.usuarioIDIndex, rowIndex, realmGet$usuarioID, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.usuarioIDIndex, rowIndex, false);
        }

        OsList fotosOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.fotosIndex);
        RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotosList = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$fotos();
        if (fotosList != null && fotosList.size() == fotosOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = fotosList.size();
            for (int i = 0; i < objects; i++) {
                com.andresivan.turistadroid.entidades.fotos.Foto fotosItem = fotosList.get(i);
                Long cacheItemIndexfotos = cache.get(fotosItem);
                if (cacheItemIndexfotos == null) {
                    cacheItemIndexfotos = com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insertOrUpdate(realm, fotosItem, cache);
                }
                fotosOsList.setRow(i, cacheItemIndexfotos);
            }
        } else {
            fotosOsList.removeAll();
            if (fotosList != null) {
                for (com.andresivan.turistadroid.entidades.fotos.Foto fotosItem : fotosList) {
                    Long cacheItemIndexfotos = cache.get(fotosItem);
                    if (cacheItemIndexfotos == null) {
                        cacheItemIndexfotos = com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insertOrUpdate(realm, fotosItem, cache);
                    }
                    fotosOsList.addRow(cacheItemIndexfotos);
                }
            }
        }

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
        long tableNativePtr = table.getNativePtr();
        LugarColumnInfo columnInfo = (LugarColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.andresivan.turistadroid.entidades.lugares.Lugar object = null;
        while (objects.hasNext()) {
            object = (com.andresivan.turistadroid.entidades.lugares.Lugar) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$nombre = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$nombre();
            if (realmGet$nombre != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nombreIndex, rowIndex, realmGet$nombre, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nombreIndex, rowIndex, false);
            }
            String realmGet$tipo = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$tipo();
            if (realmGet$tipo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.tipoIndex, rowIndex, realmGet$tipo, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.tipoIndex, rowIndex, false);
            }
            String realmGet$fecha = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$fecha();
            if (realmGet$fecha != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.fechaIndex, rowIndex, realmGet$fecha, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.fechaIndex, rowIndex, false);
            }
            String realmGet$latitud = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$latitud();
            if (realmGet$latitud != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.latitudIndex, rowIndex, realmGet$latitud, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.latitudIndex, rowIndex, false);
            }
            String realmGet$longitud = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$longitud();
            if (realmGet$longitud != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.longitudIndex, rowIndex, realmGet$longitud, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.longitudIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.valoracionIndex, rowIndex, ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$valoracion(), false);
            Table.nativeSetBoolean(tableNativePtr, columnInfo.favoritoIndex, rowIndex, ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$favorito(), false);
            String realmGet$usuarioID = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$usuarioID();
            if (realmGet$usuarioID != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.usuarioIDIndex, rowIndex, realmGet$usuarioID, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.usuarioIDIndex, rowIndex, false);
            }

            OsList fotosOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.fotosIndex);
            RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotosList = ((com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) object).realmGet$fotos();
            if (fotosList != null && fotosList.size() == fotosOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = fotosList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.andresivan.turistadroid.entidades.fotos.Foto fotosItem = fotosList.get(i);
                    Long cacheItemIndexfotos = cache.get(fotosItem);
                    if (cacheItemIndexfotos == null) {
                        cacheItemIndexfotos = com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insertOrUpdate(realm, fotosItem, cache);
                    }
                    fotosOsList.setRow(i, cacheItemIndexfotos);
                }
            } else {
                fotosOsList.removeAll();
                if (fotosList != null) {
                    for (com.andresivan.turistadroid.entidades.fotos.Foto fotosItem : fotosList) {
                        Long cacheItemIndexfotos = cache.get(fotosItem);
                        if (cacheItemIndexfotos == null) {
                            cacheItemIndexfotos = com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insertOrUpdate(realm, fotosItem, cache);
                        }
                        fotosOsList.addRow(cacheItemIndexfotos);
                    }
                }
            }

        }
    }

    public static com.andresivan.turistadroid.entidades.lugares.Lugar createDetachedCopy(com.andresivan.turistadroid.entidades.lugares.Lugar realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.andresivan.turistadroid.entidades.lugares.Lugar unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.andresivan.turistadroid.entidades.lugares.Lugar();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.andresivan.turistadroid.entidades.lugares.Lugar) cachedObject.object;
            }
            unmanagedObject = (com.andresivan.turistadroid.entidades.lugares.Lugar) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface unmanagedCopy = (com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) unmanagedObject;
        com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface realmSource = (com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$nombre(realmSource.realmGet$nombre());
        unmanagedCopy.realmSet$tipo(realmSource.realmGet$tipo());
        unmanagedCopy.realmSet$fecha(realmSource.realmGet$fecha());
        unmanagedCopy.realmSet$latitud(realmSource.realmGet$latitud());
        unmanagedCopy.realmSet$longitud(realmSource.realmGet$longitud());
        unmanagedCopy.realmSet$valoracion(realmSource.realmGet$valoracion());
        unmanagedCopy.realmSet$favorito(realmSource.realmGet$favorito());
        unmanagedCopy.realmSet$usuarioID(realmSource.realmGet$usuarioID());

        // Deep copy of fotos
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$fotos(null);
        } else {
            RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> managedfotosList = realmSource.realmGet$fotos();
            RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> unmanagedfotosList = new RealmList<com.andresivan.turistadroid.entidades.fotos.Foto>();
            unmanagedCopy.realmSet$fotos(unmanagedfotosList);
            int nextDepth = currentDepth + 1;
            int size = managedfotosList.size();
            for (int i = 0; i < size; i++) {
                com.andresivan.turistadroid.entidades.fotos.Foto item = com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.createDetachedCopy(managedfotosList.get(i), nextDepth, maxDepth, cache);
                unmanagedfotosList.add(item);
            }
        }

        return unmanagedObject;
    }

    static com.andresivan.turistadroid.entidades.lugares.Lugar update(Realm realm, LugarColumnInfo columnInfo, com.andresivan.turistadroid.entidades.lugares.Lugar realmObject, com.andresivan.turistadroid.entidades.lugares.Lugar newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface realmObjectTarget = (com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) realmObject;
        com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface realmObjectSource = (com_andresivan_turistadroid_entidades_lugares_LugarRealmProxyInterface) newObject;
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.nombreIndex, realmObjectSource.realmGet$nombre());
        builder.addString(columnInfo.tipoIndex, realmObjectSource.realmGet$tipo());
        builder.addString(columnInfo.fechaIndex, realmObjectSource.realmGet$fecha());
        builder.addString(columnInfo.latitudIndex, realmObjectSource.realmGet$latitud());
        builder.addString(columnInfo.longitudIndex, realmObjectSource.realmGet$longitud());
        builder.addInteger(columnInfo.valoracionIndex, realmObjectSource.realmGet$valoracion());
        builder.addBoolean(columnInfo.favoritoIndex, realmObjectSource.realmGet$favorito());
        builder.addString(columnInfo.usuarioIDIndex, realmObjectSource.realmGet$usuarioID());

        RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotosList = realmObjectSource.realmGet$fotos();
        if (fotosList != null) {
            RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotosManagedCopy = new RealmList<com.andresivan.turistadroid.entidades.fotos.Foto>();
            for (int i = 0; i < fotosList.size(); i++) {
                com.andresivan.turistadroid.entidades.fotos.Foto fotosItem = fotosList.get(i);
                com.andresivan.turistadroid.entidades.fotos.Foto cachefotos = (com.andresivan.turistadroid.entidades.fotos.Foto) cache.get(fotosItem);
                if (cachefotos != null) {
                    fotosManagedCopy.add(cachefotos);
                } else {
                    fotosManagedCopy.add(com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.copyOrUpdate(realm, (com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.FotoColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.fotos.Foto.class), fotosItem, true, cache, flags));
                }
            }
            builder.addObjectList(columnInfo.fotosIndex, fotosManagedCopy);
        } else {
            builder.addObjectList(columnInfo.fotosIndex, new RealmList<com.andresivan.turistadroid.entidades.fotos.Foto>());
        }

        builder.updateExistingObject();
        return realmObject;
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy aLugar = (com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aLugar.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aLugar.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aLugar.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
