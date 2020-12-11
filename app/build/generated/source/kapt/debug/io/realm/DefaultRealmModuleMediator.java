package io.realm;


import android.util.JsonReader;
import io.realm.ImportFlag;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>(3);
        modelClasses.add(com.andresivan.turistadroid.entidades.fotos.Foto.class);
        modelClasses.add(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
        modelClasses.add(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        Map<Class<? extends RealmModel>, OsObjectSchemaInfo> infoMap = new HashMap<Class<? extends RealmModel>, OsObjectSchemaInfo>(3);
        infoMap.put(com.andresivan.turistadroid.entidades.fotos.Foto.class, io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.andresivan.turistadroid.entidades.lugares.Lugar.class, io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.andresivan.turistadroid.entidades.usuario.Usuario.class, io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.getExpectedObjectSchemaInfo());
        return infoMap;
    }

    @Override
    public ColumnInfo createColumnInfo(Class<? extends RealmModel> clazz, OsSchemaInfo schemaInfo) {
        checkClass(clazz);

        if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
            return io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
            return io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
            return io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.createColumnInfo(schemaInfo);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public String getSimpleClassNameImpl(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
            return "Foto";
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
            return "Lugar";
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
            return "Usuario";
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
                return clazz.cast(new io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy());
            }
            if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
                return clazz.cast(new io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy());
            }
            if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
                return clazz.cast(new io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy());
            }
            throw getMissingProxyClassException(clazz);
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
            com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.FotoColumnInfo columnInfo = (com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.FotoColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.fotos.Foto.class);
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.copyOrUpdate(realm, columnInfo, (com.andresivan.turistadroid.entidades.fotos.Foto) obj, update, cache, flags));
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
            com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.LugarColumnInfo columnInfo = (com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.LugarColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.lugares.Lugar.class);
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.copyOrUpdate(realm, columnInfo, (com.andresivan.turistadroid.entidades.lugares.Lugar) obj, update, cache, flags));
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
            com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.UsuarioColumnInfo columnInfo = (com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.UsuarioColumnInfo) realm.getSchema().getColumnInfo(com.andresivan.turistadroid.entidades.usuario.Usuario.class);
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.copyOrUpdate(realm, columnInfo, (com.andresivan.turistadroid.entidades.usuario.Usuario) obj, update, cache, flags));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
            io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insert(realm, (com.andresivan.turistadroid.entidades.fotos.Foto) object, cache);
        } else if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
            io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.insert(realm, (com.andresivan.turistadroid.entidades.lugares.Lugar) object, cache);
        } else if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
            io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.insert(realm, (com.andresivan.turistadroid.entidades.usuario.Usuario) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
                io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insert(realm, (com.andresivan.turistadroid.entidades.fotos.Foto) object, cache);
            } else if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
                io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.insert(realm, (com.andresivan.turistadroid.entidades.lugares.Lugar) object, cache);
            } else if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
                io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.insert(realm, (com.andresivan.turistadroid.entidades.usuario.Usuario) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
                    io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
                    io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
                    io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
            io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insertOrUpdate(realm, (com.andresivan.turistadroid.entidades.fotos.Foto) obj, cache);
        } else if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
            io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.insertOrUpdate(realm, (com.andresivan.turistadroid.entidades.lugares.Lugar) obj, cache);
        } else if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
            io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.insertOrUpdate(realm, (com.andresivan.turistadroid.entidades.usuario.Usuario) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
                io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insertOrUpdate(realm, (com.andresivan.turistadroid.entidades.fotos.Foto) object, cache);
            } else if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
                io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.insertOrUpdate(realm, (com.andresivan.turistadroid.entidades.lugares.Lugar) object, cache);
            } else if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
                io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.insertOrUpdate(realm, (com.andresivan.turistadroid.entidades.usuario.Usuario) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
                    io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
                    io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
                    io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.andresivan.turistadroid.entidades.fotos.Foto.class)) {
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_fotos_FotoRealmProxy.createDetachedCopy((com.andresivan.turistadroid.entidades.fotos.Foto) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.lugares.Lugar.class)) {
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_lugares_LugarRealmProxy.createDetachedCopy((com.andresivan.turistadroid.entidades.lugares.Lugar) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.andresivan.turistadroid.entidades.usuario.Usuario.class)) {
            return clazz.cast(io.realm.com_andresivan_turistadroid_entidades_usuario_UsuarioRealmProxy.createDetachedCopy((com.andresivan.turistadroid.entidades.usuario.Usuario) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

}
