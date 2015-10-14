/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author Natus
 */

    
public enum Delimiter {
    TAB{
        @Override 
        public String toString(){ 
            return "    "; 
        } 
    },
    SPACE{
        @Override 
        public String toString(){ 
            return " "; 
        }
    },
    COMMA{
        @Override 
        public String toString(){ 
            return ","; 
        }
    }
}


