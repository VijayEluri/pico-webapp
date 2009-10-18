package example.domain;

import example.framework.Identity;
import example.utils.Lists;
import example.utils.Maps;
import example.utils.Matcher;
import org.joda.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Document {

    public enum Field {
        one, two, date
    }

    private Identity identity;
    private LocalDateTime created;
    private Map<Field, Property> properties;
    private String version;

    public Document() {
        this(new Identity());
    }

    public Document(Identity identity) {
        this(identity, new LocalDateTime());
    }

    public Document(Identity identity, LocalDateTime created) {
        this.created = created;
        this.identity = identity;
        this.properties = Maps.create();
    }

    public Identity getIdentity() {
        return identity;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Property get(Field field) {
        Property property = properties.get(field);
        return (property != null) ? property : new Property();
    }

    public void set(Field field, Property property) {
        properties.put(field, property);
    }

    public List<Field> getFields() {
        return Arrays.asList(Field.values());
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isValid() {
        return Lists.count(properties.values(), new InvalidPropertyMatcher()) == 0;
    }

    public static class InvalidPropertyMatcher implements Matcher<Property> {
        public boolean matches(Property item) {
            return !item.isValid();
        }
    }
}