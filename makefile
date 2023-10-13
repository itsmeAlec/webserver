OUT_DIR=target

clean:
	@find . -name "*.class" -type f -delete
	@rm -rf $(OUT_DIR)

server: clean
	@javac -d $(OUT_DIR) driver/ServerStartup.java

run: server
	@clear
	@java -cp target driver.ServerStartup -p 9876 -r "../docroot"