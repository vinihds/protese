/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protese.dao.conexao;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filipe
 */
public class AnnotationExclusionStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes fa) {
        String a = fa.getDeclaredClass().toString();
        String b = List.class.toString();
        
        List<Annotation> anotacoes = new ArrayList(fa.getAnnotations());
        for (int i = 0; i < anotacoes.size(); i++) {
            String an = anotacoes.get(i).toString();
            if(an.contains("javax.persistence.OneToMany") && a.equals(b)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;//clazz == List.class;
    }

}
