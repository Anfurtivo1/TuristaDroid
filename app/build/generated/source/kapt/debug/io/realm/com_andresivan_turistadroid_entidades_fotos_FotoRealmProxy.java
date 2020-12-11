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
public class com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy extends com.andresivan.turistadroid.entidades.fotos.Foto
    implements RealmObjectProxy, com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface {

    static final class FotoColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long idIndex;
        long fotoIndex;

        FotoColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Foto");
            this.idIndex = addColumnDetails("id", "id", objectSchemaInfo);
            this.fotoIndex = addColumnDetails("foto", "foto", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        FotoColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new FotoColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final FotoColumnInfo src = (FotoColumnInfo) rawSrc;
            final FotoColumnInfo dst = (FotoColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.fotoIndex = src.fotoIndex;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private FotoColumnInfo columnInfo;
    private ProxyState<com.andresivan.turistadroid.entidades.fotos.Foto> proxyState;

    com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (FotoColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.andresivan.turistadroid.entidades.fotos.Foto>(this);
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
    public String realmGet$foto() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.fotoIndex);
    }

    @Override
    public void realmSet$foto(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'foto' to null.");
            }
            row.getTable().setString(columnInfo.fotoIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            throw new IllegalArgumentException("Trying to set non-nullable field 'foto' to null.");
        }
        proxyState.getRow$realm().setString(columnInfo.fotoIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Foto", 2, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("foto", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static FotoColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new FotoColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Foto";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Foto";
    }

    @SuppressWarnings("cast")
    public static com.andresivan.turistadroid.entidades.fotos.Foto createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.andresivan.turistadroid.entidades.fotos.Foto obj = null;
        if (update) {
            Table table = realm.getTable(com.andresivan.turistadroid.entidades.fotos.Foto.class);
            FotoColumnInfo columnInfo = (FotoColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.fotos.Foto.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.fotos.Foto.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy) realm.createObjectInternal(com.andresivan.turistadroid.entidades.fotos.Foto.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy) realm.createObjectInternal(com.andresivan.turistadroid.entidades.fotos.Foto.class, json.getString("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface objProxy = (com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) obj;
        if (json.has("foto")) {
            if (json.isNull("foto")) {
                objProxy.realmSet$foto(null);
            } else {
                objProxy.realmSet$foto((String) json.getString("foto"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.andresivan.turistadroid.entidades.fotos.Foto createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.andresivan.turistadroid.entidades.fotos.Foto obj = new com.andresivan.turistadroid.entidades.fotos.Foto();
        final com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface objProxy = (com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) obj;
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
            } else if (name.equals("foto")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$foto((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$foto(null);
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

    private static com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating unexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.fotos.Foto.class), false, Collections.<String>emptyList());
        io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy obj = new io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.andresivan.turistadroid.entidades.fotos.Foto copyOrUpdate(Realm realm, FotoColumnInfo columnInfo, com.andresivan.turistadroid.entidades.fotos.Foto object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
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
            return (com.andresivan.turistadroid.entidades.fotos.Foto) cachedRealmObject;
        }

        com.andresivan.turistadroid.entidades.fotos.Foto realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.andresivan.turistadroid.entidades.fotos.Foto.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstString(pkColumnIndex, ((com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.andresivan.turistadroid.entidades.fotos.Foto copy(Realm realm, FotoColumnInfo columnInfo, com.andresivan.turistadroid.entidades.fotos.Foto newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.andresivan.turistadroid.entidades.fotos.Foto) cachedRealmObject;
        }

        com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface realmObjectSource = (com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) newObject;

        Table table = realm.getTable(com.andresivan.turistadroid.entidades.fotos.Foto.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.fotoIndex, realmObjectSource.realmGet$foto());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.andresivan.turistadroid.entidades.fotos.Foto object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.fotos.Foto.class);
        long tableNativePtr = table.getNativePtr();
        FotoColumnInfo columnInfo = (FotoColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.fotos.Foto.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$foto = ((com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) object).realmGet$foto();
        if (realmGet$foto != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fotoIndex, rowIndex, realmGet$foto, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.fotos.Foto.class);
        long tableNativePtr = table.getNativePtr();
        FotoColumnInfo columnInfo = (FotoColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.fotos.Foto.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.andresivan.turistadroid.entidades.fotos.Foto object = null;
        while (objects.hasNext()) {
            object = (com.andresivan.turistadroid.entidades.fotos.Foto) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$foto = ((com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) object).realmGet$foto();
            if (realmGet$foto != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.fotoIndex, rowIndex, realmGet$foto, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.andresivan.turistadroid.entidades.fotos.Foto object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.fotos.Foto.class);
        long tableNativePtr = table.getNativePtr();
        FotoColumnInfo columnInfo = (FotoColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.fotos.Foto.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$foto = ((com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) object).realmGet$foto();
        if (realmGet$foto != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.fotoIndex, rowIndex, realmGet$foto, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.fotoIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.fotos.Foto.class);
        long tableNativePtr = table.getNativePtr();
        FotoColumnInfo columnInfo = (FotoColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.fotos.Foto.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.andresivan.turistadroid.entidades.fotos.Foto object = null;
        while (objects.hasNext()) {
            object = (com.andresivan.turistadroid.entidades.fotos.Foto) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, (String)primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$foto = ((com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) object).realmGet$foto();
            if (realmGet$foto != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.fotoIndex, rowIndex, realmGet$foto, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.fotoIndex, rowIndex, false);
            }
        }
    }

    public static com.andresivan.turistadroid.entidades.fotos.Foto createDetachedCopy(com.andresivan.turistadroid.entidades.fotos.Foto realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.andresivan.turistadroid.entidades.fotos.Foto unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.andresivan.turistadroid.entidades.fotos.Foto();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.andresivan.turistadroid.entidades.fotos.Foto) cachedObject.object;
            }
            unmanagedObject = (com.andresivan.turistadroid.entidades.fotos.Foto) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface unmanagedCopy = (com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) unmanagedObject;
        com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface realmSource = (com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$foto(realmSource.realmGet$foto());

        return unmanagedObject;
    }

    static com.andresivan.turistadroid.entidades.fotos.Foto update(Realm realm, FotoColumnInfo columnInfo, com.andresivan.turistadroid.entidades.fotos.Foto realmObject, com.andresivan.turistadroid.entidades.fotos.Foto newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface realmObjectTarget = (com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) realmObject;
        com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface realmObjectSource = (com_andresivan_turistadroid_entidades_fotos_FotoRealmProxyInterface) newObject;
        Table table = realm.getTable(com.andresivan.turistadroid.entidades.fotos.Foto.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.idIndex, realmObjectSource.realmGet$id());
        builder.addString(columnInfo.fotoIndex, realmObjectSource.realmGet$foto());

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
        com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy aFoto = (com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aFoto.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aFoto.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aFoto.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
