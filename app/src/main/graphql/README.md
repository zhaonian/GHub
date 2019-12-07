Fetched and formatted `schema.json` by running this script:
```console
curl -H "Authorization: bearer ${GithubToken}" https://api.github.com/graphql | python -m json.tool >> schema.json
```
