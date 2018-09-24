package ar.com.sac.mutant;

import ar.com.sac.human.Human;
import junit.framework.TestCase;

public class MutantCheckerTestCase extends TestCase{

	// Test facilitado por enunciado ("caso base")
	public void testIsMutant(){
		Human mutant = new Human();
		mutant.setDna(new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
		assertTrue(MutantChecker.isMutant(mutant.getDna()));
	}
	
	public void testIsMutantHorizontalScan(){
		Human mutant = new Human();
		mutant.setDna(new String[]{"AAAAAA","CCCCCC","TTATCT","CTTACG","CCTATA","TCACTG"});
		assertTrue(MutantChecker.isMutant(mutant.getDna()));
	}

	public void testIsMutantVerticalScan(){
		Human mutant = new Human();
		mutant.setDna(new String[]{"AAATAC","ATCCCC","ATATCC","ATTACC","CCTATC","TCACTG"});
		assertTrue(MutantChecker.isMutant(mutant.getDna()));
	}

	public void testIsMutantDiagnonalScan(){
		Human mutant = new Human();
		mutant.setDna(new String[]{"ATGA","GAAT","CAAT","ATTA"});
		assertTrue(MutantChecker.isMutant(mutant.getDna()));
	}
	
	public void testIsNotMutant(){
		Human human = new Human();
		human.setDna(new String[]{"ATGGGA","CAGTGC","ATATAG","AGAAGT","CACCTA","TCAATG"});
		assertFalse(MutantChecker.isMutant(human.getDna()));
	}
	
	public void testIsNotMutantBecauseHasBadSequenceLetter(){
		Human crazyHuman = new Human();
		String badSequence = "CAXXGC";
		String[] dna = new String[]{"ATGGGA",badSequence,"ATATGG","AGAAGT","CACCTA","TCAATG"};
		crazyHuman.setDna(dna);
		try {
			MutantChecker.isMutant(crazyHuman.getDna());
			fail();
		}
		catch(RuntimeException e) {
			assertTrue(e.getMessage().contains(badSequence));
		}
	}
	
	public void testIsNotMutantBecauseHasBadNxNDnaMatrix(){
		Human crazyHuman = new Human();
		String[] dna = new String[]{"A","CAGTGC","ATAG","AGAAGT","CACCTA"};
		crazyHuman.setDna(dna);
		try {
			MutantChecker.isMutant(crazyHuman.getDna());
			fail();
		}catch(RuntimeException e) {
			assertTrue(e.getMessage().equals(MutantChecker.ERROR_MESSAGE_NXN));
		}
	}	
	
}
