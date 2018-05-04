package zy.xuminghang.util;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * 升级数据库
 */
public class CustomMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 1 && newVersion == 2) {
            RealmObjectSchema personSchema = schema.get("NewCommodity");
            //新增@Required的id
            personSchema
                    .addField("hwbh", String.class)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {

                        }


                    });

            oldVersion++;
        }
    }
}
