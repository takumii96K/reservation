package org.example.reservation.entity.converter;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.reservation.entity.enumeration.Category;
import org.springframework.stereotype.Component;

@Component
@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category category) {
        // Enumがnullの場合は、DBカラムもnullに
        if (category == null) {
            return null;
        }
        // Enumの名前をDBカラムに保存
        return category.name();
    }

    @Override
    public Category convertToEntityAttribute(String dbData) {
        // DBカラムがnullの場合は、Enumもnullに
        if (dbData == null) {
            return null;
        }
        // DBカラムの値に基づいてEnumを取得
        try {
            return Category.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            // DBに存在するがEnumには定義されていないカテゴリがある場合、例外を投げる
            throw new IllegalArgumentException("Unknown category: " + dbData);
        }
    }
}

