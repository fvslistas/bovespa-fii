package br.com.verly.bovespafii;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVFormat.Builder;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class FiiCsvReader {
	public List<Fii> readFile(File csvFile) throws Exception {
		return recordsToFii(csvFile);
	}

	private final String SEPARADOR = ";";

	private List<Fii> recordsToFii(File csvFile) throws IOException {
		List<Fii> fiis = new ArrayList<Fii>();

		Builder b = CSVFormat.DEFAULT.builder();
		CSVFormat f = b.setDelimiter(SEPARADOR).build();
		CSVParser records = f.parse(new FileReader(csvFile));

		Iterator<CSVRecord> i = records.iterator();
		i.next(); // pula o header
		while(i.hasNext()) {
			fiis.add(recordToFii(i.next()));
		}
		return fiis;
	}

	private Fii recordToFii(CSVRecord record) {
		// ordem: "razao", "fundo", "segmento", "codigo"
		Fii fii = new Fii(record.get(0), record.get(1), record.get(2), record.get(3));
		return fii;
	}
}