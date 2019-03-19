
#Init git
git init

#Clone repo
git clone <repourl>

#Add remote origin url
git remote -v
git add origin <repourl>
git remote origin <repourl>
git remote -v


#To create a tag
e.g : git tag <name>
git tag v1.0

#To push tag to remote origin
git push origin v1.0

#To view tags
git tag

#To fetch the tag code
git clone <remote-url>
git fetch && git fetch --tags
git checkout v1.0



#delete tag
git tag -D <tag>


#Switch to other branches
git checkout <branchname>

#Create New Branch
git checkout -b <newbranchname>

#create new branch with cloning copy of existing branch
git checkout -b <newbranch> <branchname>


#Merge branch to master
git merge <branchname>

#delete branch
git checkout -D <branch>


#Move changes to other branch
git stash
git checkout -b <branch>
git stash pop


#Commit code changes to a branch
touch newFile.txt
git add .  
or 
git add --all
or
git add newFile.txt

git status

git commit -m "created new file"

git push origin <branch>


#Pull latest branch code or origin code
git pull origin <branch> 



#To Check the commit log
git log --pretty=online

#To check code difference
git diff
