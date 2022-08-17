package dev.xdark.clientapi.config;

import dev.xdark.clientapi.Side;
import dev.xdark.clientapi.SidedApi;
import dev.xdark.clientapi.entry.ModMain;
import lombok.SneakyThrows;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@SidedApi(Side.BOTH)
public interface ConfigFactory {

    static Map<String, Config> cache = new HashMap<>();

    static Config create(String name, ModMain mod) {
        return create(name, name, mod);
    };

    static Config create(String name, String resource, ModMain mod) {
        Class<? extends ModMain> modClass = mod == null ? null : mod.getClass();
        return new ConfigImpl(name, resource, modClass);
    }

    @SidedApi(Side.BOTH)
    public static class ConfigImpl implements Config {

        protected static File folder;
        static {
            folder = new File(System.getProperty("user.dir"), "config");
            if (!folder.exists()) folder.mkdirs();
        }

        protected File file;
        protected String resource;
        protected Class<? extends ModMain> modClass;
        protected Properties properties = new Properties();
        protected Map<String, Object> values = new ConcurrentHashMap<>();

        @SneakyThrows
        ConfigImpl(String name, String resource, Class<? extends ModMain> modClass) {
            file = new File(folder, name + ".properties");

            this.modClass = modClass;
            this.resource = resource;

            boolean exists = file.exists();
            if (exists)
                load();
            else {
                file.createNewFile();
                setDefaults();
            }
        }

        public void setDefaults() {
            if (modClass == null) return;
            InputStream def = modClass.getResourceAsStream(resource + ".properties");
            if (def != null)
                load(def);
        }

        @SneakyThrows
        public void save() {
            values.forEach((p, o) -> properties.setProperty(p, o.toString()));
            OutputStream outputStream = new FileOutputStream(file);
            properties.save(outputStream, "Save");
            outputStream.close();
        }

        @SneakyThrows
        public void load() {
            InputStream inputStream = new FileInputStream(file);
            load(inputStream);
        }

        @SneakyThrows
        public void load(InputStream inputStream) {
            properties.load(inputStream);
            inputStream.close();
            values.clear();
            properties.forEach((path, obj) -> {
                values.put(path.toString(), obj);
            });
        }

        public File getFile() {
            return file;
        }

        public void set(String path, Object value) {
            values.put(path, value);
        }

        public Object get(String path) {
            return get(path, null);
        }

        public Object get(String path, Object obj) {
            return values.getOrDefault(path, obj);
        }

        public String getString(String path) {
            return getString(path, "");
        }

        public String getString(String path, String string) {
            return get(path, string).toString();
        }

        public Integer getInteger(String path) {
            return getInteger(path, 0);
        }

        public Integer getInteger(String path, int integer) {
            try {
                return Integer.parseInt(getString(path));
            } catch (Throwable e) {
                return integer;
            }
        }

        public Double getDouble(String path) {
            return getDouble(path, 0.0);
        }

        public Double getDouble(String path, double d0uble) {
            try {
                return Double.parseDouble(getString(path));
            } catch (Throwable e) {
                return d0uble;
            }
        }

        public Boolean getBoolean(String path) {
            return getBoolean(path, false);
        }

        public Boolean getBoolean(String path, boolean bool) {
            return get(path) == null ? bool : Boolean.parseBoolean(getString(path));
        }
    }
}
