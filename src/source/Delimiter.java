/*
 * @author Nathan Castlehow
 * @version 1.0
 * ENUM CLASS for delimiters
 */
package source;


    
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


