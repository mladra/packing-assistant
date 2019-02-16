package pl.lodz.p.edu.converters;

import java.util.List;

import androidx.databinding.InverseMethod;
import pl.lodz.p.edu.database.entity.definitions.PackingListDefinition;

public class PackingListDefinitionConverter {

    private static final String EMPTY = "";
    private List<PackingListDefinition> definitions;

    public PackingListDefinitionConverter(List<PackingListDefinition> definitions) {
        this.definitions = definitions;
    }

    @InverseMethod("toObject")
    public String toString(PackingListDefinition value) {
        return value == null ? EMPTY : value.getName();
    }

    public PackingListDefinition toObject(String value) {
        if (definitions != null && !definitions.isEmpty()) {
            for (final PackingListDefinition packingListDefinition : definitions) {
                if (packingListDefinition.getName().toLowerCase().startsWith(value.toLowerCase())) {
                    return packingListDefinition;
                }
            }
        }
        return null;
    }

}
