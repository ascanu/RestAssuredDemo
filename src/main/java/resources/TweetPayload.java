package resources;

public class TweetPayload 
{

	public static String TweetProjectCreationPayload()
	{
		
		String Bo="{    \"key\": \"EX1111\",\r\n" + 
				"    \"name\": \"Example11\",\r\n" + 
				"    \"projectTypeKey\": \"business\",\r\n" + 
				"    \"projectTemplateKey\": \"c-core-project-management\",\r\n" + 
				"    \"description\": \"Example Project description\",\r\n" + 
				"    \"lead\": \"anupam\",\r\n" +				
				"    \"assigneeType\": \"PROJECT_LEAD\",\r\n" + 
				"    \"avatarId\": 10200\r\n" + 
				"  }";

		return Bo;
		
		}
	}