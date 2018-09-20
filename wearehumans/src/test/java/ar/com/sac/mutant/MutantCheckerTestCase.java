package ar.com.sac.mutant;

import org.springframework.util.StringUtils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

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
	
	public void testIsNotMutant(){
		Human human = new Human();
		human.setDna(new String[]{"ATGGGA","CAGTGC","ATATAG","AGAAGT","CACCTA","TCAATG"});
		assertFalse(MutantChecker.isMutant(human.getDna()));
	}
	
	public void testIsNotMutantBecauseHasBadSequenceLetter(){
		Human crazyHuman = new Human();
		crazyHuman.setDna(new String[]{"ATGGGA","CAXXGC","ATATGG","AGAAGT","CACCTA","TCAATG"});
		assertFalse(MutantChecker.isMutant(crazyHuman.getDna()));
	}
	
	public void testIsNotMutantBecauseHasBadNxNDnaMatrix(){
		Human crazyHuman = new Human();
		crazyHuman.setDna(new String[]{"A","CAGTGC","ATAG","AGAAGT","CACCTA"});
		assertFalse(MutantChecker.isMutant(crazyHuman.getDna()));
	}	
	
}
