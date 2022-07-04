package br.com.verly.bovespafii;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/fii") // This means URL's start with /demo (after Application path)
public class FiiController {
	@Autowired
	private FiiRepository fiiRepository;

	@Autowired
	private FileStorageService fileStorageService;

	private FiiCsvReader fiiCsvReader = new FiiCsvReader();

	@PostMapping(path = "/add")
	public @ResponseBody String add(@RequestParam String codigo, @RequestParam String fundo, @RequestParam String razao,
			@RequestParam String segmento) {
		Fii f = new Fii(razao, fundo, segmento, codigo);
		fiiRepository.save(f);
		return "Saved";
	}

	@PostMapping("/upload")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);

		return new UploadFileResponse(fileName, file.getContentType(), file.getSize());
	}

	@PostMapping("/carregar")
	public String carregar() {
		try {
			List<Fii> fiis = fiiCsvReader.readFile(fileStorageService.file());
			fiiRepository.saveAll(fiis);

			return "Arquivo gravado no BD";
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return "Problemas para gravar arquivo no BD";
		}
	}

	@GetMapping(path = "/list")
	public @ResponseBody Iterable<Fii> list() {
		return fiiRepository.findAll();
	}
}