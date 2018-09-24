package ar.com.sac.mutant;

import org.apache.commons.lang3.StringUtils;

public class MutantChecker {

	public static final String ERROR_MESSAGE_NXN = "La matriz no posee un dimensiones NxN válidas";
	private static final String C_SEQUENCE = "CCCC";
	private static final String T_SEQUENCE = "TTTT";
	private static final String G_SEQUENCE = "GGGG";
	private static final String A_SEQUENCE = "AAAA";
	private static final int MAX_MUTANT_SEQUENCE = 2;
	private static final String VALID_SEQUENCE_LETTERS = "ATCG";
	
	/**
	 * Detecta si un humano es mutante basándose en su secuencia de ADN
	 * @param dna (NXN)
	 * @return boolean 
	 */
	public static boolean isMutant(String[] dna){
		checkValidSequences(dna);
		checkValidNXNDnaMatrix(dna);
		return hasALotOfMutantSequences(dna);
	}
	
	/**
	 * Es mutante si encuentras más de "MAX_MUTANTS_SEQUENCE" secuencia de "MAX_CONCAT_LETTERS" letras
	 * iguales de forma oblicua, horizontal o vertical. Escaneamos la matriz NxN en busca de de ellas.
	 * Primeramente se escanea de forma horizontal, luego vertical y oblicua. Si cumple con la condicion corta el algoritmo (para no seguir procesando en vano)
	 * @param matrix
	 * @return boolean
	 */
	private static boolean hasALotOfMutantSequences(String[] dna){
		char[][] matrix = transformToNxNMatrix(dna);

		// Horizontal Scan
		int mutantSequences = 0;
		for(int i=0; i < matrix[0].length; i++){
			mutantSequences = mutantSequences + countMutantSequences(new String(matrix[i]));
			if(mutantSequences >= MAX_MUTANT_SEQUENCE)
				return true;
		}

		// Vertical Scan
		for(int y=0; y < matrix[0].length; y++){
			String word="";
			for(int x=0; x < matrix[0].length; x++){
				char letter = matrix[x][y];
				word = word + letter;
			}
			mutantSequences = mutantSequences + countMutantSequences(word);
			if(mutantSequences >= MAX_MUTANT_SEQUENCE)
				return true;
		}
		
		// diagonal Scan "\"
		for(int x=0; x < matrix[0].length; x++) {
			String diagonal = ""; 
			for(int y=0; y < matrix[0].length - x ; y++){
				diagonal = diagonal + matrix[x+y][y];
			}
			mutantSequences = mutantSequences + countMutantSequences(diagonal);
			if(mutantSequences >= MAX_MUTANT_SEQUENCE)
				return true;
		}
		for(int y=1; y < matrix[0].length; y++) {
			String diagonal = ""; 
			for(int x=0; x < matrix[0].length - y; x++){
				diagonal = diagonal + matrix[x][y+x];
			}
			mutantSequences = mutantSequences + countMutantSequences(diagonal);
			if(mutantSequences >= MAX_MUTANT_SEQUENCE)
				return true;
		}
		
		// diagonal Scan "/" 
		for(int x=0; x < matrix[0].length ; x++) {
			String diagonal = ""; 
			for(int y=0; y < matrix[0].length -x ; y++) {
				diagonal = diagonal + matrix[matrix[0].length-1-x-y][y];
			}
			mutantSequences = mutantSequences + countMutantSequences(diagonal);
			if(mutantSequences >= MAX_MUTANT_SEQUENCE)
				return true;
		}
		
		for(int y=1; y < matrix[0].length; y++) {
			String diagonal = "";
			for(int x=0; x < matrix[0].length - y; x++){
				diagonal = diagonal + matrix[matrix[0].length-1-x][y+x];
			}
			mutantSequences = mutantSequences + countMutantSequences(diagonal);
			if(mutantSequences >= MAX_MUTANT_SEQUENCE)
				return true;
		}
			
		return false;
	}
	
	///////////////////////// Internal Utils //////////////////////////////////////////
	
	private static char[][] transformToNxNMatrix(String[] dna){

		int n = dna.length;
		char[][] matrix =  new char[n][n] ;
		int i = 0;
		
		for (String sequence: dna) {
			matrix[i++] = sequence.toCharArray(); 
		}
		return matrix;
	}
	
	private static int countMutantSequences(String sequence) {
		return StringUtils.countMatches(sequence,C_SEQUENCE) + StringUtils.countMatches(sequence,A_SEQUENCE) + StringUtils.countMatches(sequence,G_SEQUENCE) + StringUtils.countMatches(sequence,T_SEQUENCE) ;
	}
	
	//////////////////////// Validations ///////////////////////////////
	
	/**
	 * Todas las sequencias deben contener caracteres validos.
	 * @param dna
	 */
	private static void checkValidSequences(String[] dna){
		
		for (String sequence: dna) {
			if(!StringUtils.containsOnly(sequence,VALID_SEQUENCE_LETTERS))
				throw new RuntimeException("La matriz posee caracteres que no corresponden al dominio. Secuencia " + sequence );
		}
	}
	
	/**
	 * La matriz debe ser NxN 
	 * @param dna
	 * @return
	 */
	private static void checkValidNXNDnaMatrix(String[] dna){
		
		for (String sequence: dna) {
			if(sequence.length() != dna.length)
				throw new RuntimeException(ERROR_MESSAGE_NXN);
		}
	} 	
}
