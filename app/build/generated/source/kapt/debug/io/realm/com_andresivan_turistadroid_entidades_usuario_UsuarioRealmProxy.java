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
public class com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy extends com.andresivan.turistadroid.entidades.usuario.Usuario
    implements RealmObjectProxy, com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface {

    static final class UsuarioColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long idIndex;
        long correoIndex;
        long contrasenaIndex;
        long nombreIndex;
        long fotoUsuarioIndex;
        long cuentaTwitterIndex;

        UsuarioColumnInfo(OsSchemaInfo schemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Usuario");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.correoIndex = addColumnDetails("correo", "correo", objectSchemaInfo);
            this.contrasenaIndex = addColumnDetails("contrasena", "contrasena", objectSchemaInfo);
            this.nombreIndex = addColumnDetails("nombre", "nombre", objectSchemaInfo);
            this.fotoUsuarioIndex = addColumnDetails("fotoUsuario", "fotoUsuario", objectSchemaInfo);
            this.cuentaTwitterIndex = addColumnDetails("cuentaTwitter", "cuentaTwitter", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        UsuarioColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new UsuarioColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final UsuarioColumnInfo src = (UsuarioColumnInfo) rawSrc;
            final UsuarioColumnInfo dst = (UsuarioColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.correoIndex = src.correoIndex;
            dst.contrasenaIndex = src.contrasenaIndex;
            dst.nombreIndex = src.nombreIndex;
            dst.fotoUsuarioIndex = src.fotoUsuarioIndex;
            dst.cuentaTwitterIndex = src.cuentaTwitterIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private UsuarioColumnInfo columnInfo;
    private ProxyState<com.andresivan.turistadroid.entidades.usuario.Usuario> proxyState;

    com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (UsuarioColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.andresivan.turistadroid.entidades.usuario.Usuario>(this);
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
    public String realmGet$correo() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.correoIndex);
    }

    @Override
    public void realmSet$correo(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'correo' to null.");
            }
            row.getTable().setString(columnInfo.correoIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'correo' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.correoIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$contrasena() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.contrasenaIndex);
    }

    @Override
    public void realmSet$contrasena(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'contrasena' to null.");
            }
            row.getTable().setString(columnInfo.contrasenaIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'contrasena' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.contrasenaIndex, value);
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
    public String realmGet$fotoUsuario() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.fotoUsuarioIndex);
    }

    @Override
    public void realmSet$fotoUsuario(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'fotoUsuario' to null.");
            }
            row.getTable().setString(columnInfo.fotoUsuarioIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'fotoUsuario' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.fotoUsuarioIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$cuentaTwitter() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.cuentaTwitterIndex);
    }

    @Override
    public void realmSet$cuentaTwitter(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'cuentaTwitter' to null.");
            }
            row.getTable().setString(columnInfo.cuentaTwitterIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'cuentaTwitter' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.cuentaTwitterIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Usuario", 6, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("correo", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("contrasena", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("nombre", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("fotoUsuario", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("cuentaTwitter", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static UsuarioColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new UsuarioColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Usuario";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Usuario";
    }

    @SuppressWarnings("cast")
    public static com.andresivan.turistadroid.entidades.usuario.Usuario createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.andresivan.turistadroid.entidades.usuario.Usuario obj = null;
        if (update) {
            Table table = realm.getTable(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
            UsuarioColumnInfo columnInfo = (UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.usuario.Usuario.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy) realm.createObjectInternal(com.andresivan.turistadroid.entidades.usuario.Usuario.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy) realm.createObjectInternal(com.andresivan.turistadroid.entidades.usuario.Usuario.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface objProxy = (com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) obj;
        if (json.has("correo")) {
            if (json.isNull("correo")) {
                objProxy.realmSet$correo(null);
            } else {
                objProxy.realmSet$correo((String) json.getString("correo"));
            }
        }
        if (json.has("contrasena")) {
            if (json.isNull("contrasena")) {
                objProxy.realmSet$contrasena(null);
            } else {
                objProxy.realmSet$contrasena((String) json.getString("contrasena"));
            }
        }
        if (json.has("nombre")) {
            if (json.isNull("nombre")) {
                objProxy.realmSet$nombre(null);
            } else {
                objProxy.realmSet$nombre((String) json.getString("nombre"));
            }
        }
        if (json.has("fotoUsuario")) {
            if (json.isNull("fotoUsuario")) {
                objProxy.realmSet$fotoUsuario(null);
            } else {
                objProxy.realmSet$fotoUsuario((String) json.getString("fotoUsuario"));
            }
        }
        if (json.has("cuentaTwitter")) {
            if (json.isNull("cuentaTwitter")) {
                objProxy.realmSet$cuentaTwitter(null);
            } else {
                objProxy.realmSet$cuentaTwitter((String) json.getString("cuentaTwitter"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.andresivan.turistadroid.entidades.usuario.Usuario createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.andresivan.turistadroid.entidades.usuario.Usuario obj = new com.andresivan.turistadroid.entidades.usuario.Usuario();
        final com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface objProxy = (com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) obj;
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
            } else if (name.equals("correo")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$correo((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$correo(null);
                }
            } else if (name.equals("contrasena")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$contrasena((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$contrasena(null);
                }
            } else if (name.equals("nombre")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$nombre((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$nombre(null);
                }
            } else if (name.equals("fotoUsuario")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$fotoUsuario((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$fotoUsuario(null);
                }
            } else if (name.equals("cuentaTwitter")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$cuentaTwitter((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$cuentaTwitter(null);
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

    private static com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.usuario.Usuario.class), false, Collections.<String>emptyList());
        io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy obj = new io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.andresivan.turistadroid.entidades.usuario.Usuario copyOrUpdate(Realm realm, UsuarioColumnInfo columnInfo, com.andresivan.turistadroid.entidades.usuario.Usuario object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.andresivan.turistadroid.entidades.usuario.Usuario) cachedRealmObject;
        }

        com.andresivan.turistadroid.entidades.usuario.Usuario realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstString(pkColumnIndex, ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.andresivan.turistadroid.entidades.usuario.Usuario copy(Realm realm, UsuarioColumnInfo columnInfo, com.andresivan.turistadroid.entidades.usuario.Usuario newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.andresivan.turistadroid.entidades.usuario.Usuario) cachedRealmObject;
        }

        com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface realmObjectSource = (com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) newObject;

        Table table = realm.getTable(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.correoIndex, realmObjectSource.realmGet$correo());
        builder.addString(columnInfo.contrasenaIndex, realmObjectSource.realmGet$contrasena());
        builder.addString(columnInfo.nombreIndex, realmObjectSource.realmGet$nombre());
        builder.addString(columnInfo.fotoUsuarioIndex, realmObjectSource.realmGet$fotoUsuario());
        builder.addString(columnInfo.cuentaTwitterIndex, realmObjectSource.realmGet$cuentaTwitter());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.andresivan.turistadroid.entidades.usuario.Usuario object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
        long tableNativePtr = table.getNativePtr();
        UsuarioColumnInfo columnInfo = (UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$correo = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$correo();
        if (realmGet$correo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.correoIndex, rowIndex, realmGet$correo, false);
        }
        String realmGet$contrasena = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$contrasena();
        if (realmGet$contrasena != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contrasenaIndex, rowIndex, realmGet$contrasena, false);
        }
        String realmGet$nombre = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$nombre();
        if (realmGet$nombre != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nombreIndex, rowIndex, realmGet$nombre, false);
        }
        String realmGet$fotoUsuario = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$fotoUsuario();
        if (realmGet$fotoUsuario != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fotoUsuarioIndex, rowIndex, realmGet$fotoUsuario, false);
        }
        String realmGet$cuentaTwitter = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$cuentaTwitter();
        if (realmGet$cuentaTwitter != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cuentaTwitterIndex, rowIndex, realmGet$cuentaTwitter, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
        long tableNativePtr = table.getNativePtr();
        UsuarioColumnInfo columnInfo = (UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.andresivan.turistadroid.entidades.usuario.Usuario object = null;
        while (objects.hasNext()) {
            object = (com.andresivan.turistadroid.entidades.usuario.Usuario) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$correo = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$correo();
            if (realmGet$correo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.correoIndex, rowIndex, realmGet$correo, false);
            }
            String realmGet$contrasena = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$contrasena();
            if (realmGet$contrasena != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.contrasenaIndex, rowIndex, realmGet$contrasena, false);
            }
            String realmGet$nombre = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$nombre();
            if (realmGet$nombre != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nombreIndex, rowIndex, realmGet$nombre, false);
            }
            String realmGet$fotoUsuario = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$fotoUsuario();
            if (realmGet$fotoUsuario != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.fotoUsuarioIndex, rowIndex, realmGet$fotoUsuario, false);
            }
            String realmGet$cuentaTwitter = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$cuentaTwitter();
            if (realmGet$cuentaTwitter != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cuentaTwitterIndex, rowIndex, realmGet$cuentaTwitter, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.andresivan.turistadroid.entidades.usuario.Usuario object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
        long tableNativePtr = table.getNativePtr();
        UsuarioColumnInfo columnInfo = (UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$correo = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$correo();
        if (realmGet$correo != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.correoIndex, rowIndex, realmGet$correo, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.correoIndex, rowIndex, false);
        }
        String realmGet$contrasena = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$contrasena();
        if (realmGet$contrasena != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.contrasenaIndex, rowIndex, realmGet$contrasena, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.contrasenaIndex, rowIndex, false);
        }
        String realmGet$nombre = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$nombre();
        if (realmGet$nombre != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nombreIndex, rowIndex, realmGet$nombre, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nombreIndex, rowIndex, false);
        }
        String realmGet$fotoUsuario = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$fotoUsuario();
        if (realmGet$fotoUsuario != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fotoUsuarioIndex, rowIndex, realmGet$fotoUsuario, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fotoUsuarioIndex, rowIndex, false);
        }
        String realmGet$cuentaTwitter = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$cuentaTwitter();
        if (realmGet$cuentaTwitter != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cuentaTwitterIndex, rowIndex, realmGet$cuentaTwitter, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.cuentaTwitterIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
        long tableNativePtr = table.getNativePtr();
        UsuarioColumnInfo columnInfo = (UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.andresivan.turistadroid.entidades.usuario.Usuario object = null;
        while (objects.hasNext()) {
            object = (com.andresivan.turistadroid.entidades.usuario.Usuario) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$correo = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$correo();
            if (realmGet$correo != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.correoIndex, rowIndex, realmGet$correo, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.correoIndex, rowIndex, false);
            }
            String realmGet$contrasena = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$contrasena();
            if (realmGet$contrasena != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.contrasenaIndex, rowIndex, realmGet$contrasena, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.contrasenaIndex, rowIndex, false);
            }
            String realmGet$nombre = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$nombre();
            if (realmGet$nombre != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nombreIndex, rowIndex, realmGet$nombre, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nombreIndex, rowIndex, false);
            }
            String realmGet$fotoUsuario = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$fotoUsuario();
            if (realmGet$fotoUsuario != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.fotoUsuarioIndex, rowIndex, realmGet$fotoUsuario, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.fotoUsuarioIndex, rowIndex, false);
            }
            String realmGet$cuentaTwitter = ((com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) object).realmGet$cuentaTwitter();
            if (realmGet$cuentaTwitter != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cuentaTwitterIndex, rowIndex, realmGet$cuentaTwitter, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.cuentaTwitterIndex, rowIndex, false);
            }
        }
    }

    public static com.andresivan.turistadroid.entidades.usuario.Usuario createDetachedCopy(com.andresivan.turistadroid.entidades.usuario.Usuario realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.andresivan.turistadroid.entidades.usuario.Usuario unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.andresivan.turistadroid.entidades.usuario.Usuario();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.andresivan.turistadroid.entidades.usuario.Usuario) cachedObject.object;
            }
            unmanagedObject = (com.andresivan.turistadroid.entidades.usuario.Usuario) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface unmanagedCopy = (com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) unmanagedObject;
        com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface realmSource = (com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$correo(realmSource.realmGet$correo());
        unmanagedCopy.realmSet$contrasena(realmSource.realmGet$contrasena());
        unmanagedCopy.realmSet$nombre(realmSource.realmGet$nombre());
        unmanagedCopy.realmSet$fotoUsuario(realmSource.realmGet$fotoUsuario());
        unmanagedCopy.realmSet$cuentaTwitter(realmSource.realmGet$cuentaTwitter());

        return unmanagedObject;
    }

    static com.andresivan.turistadroid.entidades.usuario.Usuario update(Realm realm, UsuarioColumnInfo columnInfo, com.andresivan.turistadroid.entidades.usuario.Usuario realmObject, com.andresivan.turistadroid.entidades.usuario.Usuario newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface realmObjectTarget = (com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) realmObject;
        com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface realmObjectSource = (com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxyInterface) newObject;
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.correoIndex, realmObjectSource.realmGet$correo());
        builder.addString(columnInfo.contrasenaIndex, realmObjectSource.realmGet$contrasena());
        builder.addString(columnInfo.nombreIndex, realmObjectSource.realmGet$nombre());
        builder.addString(columnInfo.fotoUsuarioIndex, realmObjectSource.realmGet$fotoUsuario());
        builder.addString(columnInfo.cuentaTwitterIndex, realmObjectSource.realmGet$cuentaTwitter());

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
        com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy aUsuario = (com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aUsuario.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aUsuario.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aUsuario.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
