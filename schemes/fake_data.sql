
SET IDENTITY_INSERT [dbo].[PermissionRequest] ON 

INSERT [dbo].[PermissionRequest] ([PermissionRequestID], [Name], [RequestUrl], [Method]) VALUES (6, N'Dashboard', N'http://localhost:8080/OnlineLearning/management/dashboard', N'GET')
INSERT [dbo].[PermissionRequest] ([PermissionRequestID], [Name], [RequestUrl], [Method]) VALUES (7, N'Subject Detail', N'http://localhost:8080/OnlineLearning/management/subject-detail', N'GET')
INSERT [dbo].[PermissionRequest] ([PermissionRequestID], [Name], [RequestUrl], [Method]) VALUES (8, N'Delete Subject Detail', N'http://localhost:8080/OnlineLearning/management/subject-detail', N'DELETE')
INSERT [dbo].[PermissionRequest] ([PermissionRequestID], [Name], [RequestUrl], [Method]) VALUES (9, N'Dimension Detail', N'http://localhost:8080/OnlineLearning/management/dimension-detail', N'GET')
INSERT [dbo].[PermissionRequest] ([PermissionRequestID], [Name], [RequestUrl], [Method]) VALUES (12, N'Dimension Detail', N'http://localhost:8080/OnlineLearning/management/dimension-detail', N'POST')
INSERT [dbo].[PermissionRequest] ([PermissionRequestID], [Name], [RequestUrl], [Method]) VALUES (13, N'View Subject List', N'http://localhost:8080/OnlineLearning/management/subject-list', N'GET')
INSERT [dbo].[PermissionRequest] ([PermissionRequestID], [Name], [RequestUrl], [Method]) VALUES (14, N'Filter Subject List', N'http://localhost:8080/OnlineLearning/management/subject-list', N'POST')
INSERT [dbo].[PermissionRequest] ([PermissionRequestID], [Name], [RequestUrl], [Method]) VALUES (15, N'Delete Subject', N'http://localhost:8080/OnlineLearning/management/subject-list', N'DELETE')
INSERT [dbo].[PermissionRequest] ([PermissionRequestID], [Name], [RequestUrl], [Method]) VALUES (16, N'Edit Price Package', N'http://localhost:8080/OnlineLearning/management/price-package-detail', N'GET')
INSERT [dbo].[PermissionRequest] ([PermissionRequestID], [Name], [RequestUrl], [Method]) VALUES (17, N'Edit Price Package', N'http://localhost:8080/OnlineLearning/management/price-package-detail', N'POST')
SET IDENTITY_INSERT [dbo].[PermissionRequest] OFF
GO
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([RoleID], [Name], [Order], [Status], [type]) VALUES (1, N'EXPERT', 2, 1, N'USER_ROLE')
INSERT [dbo].[Role] ([RoleID], [Name], [Order], [Status], [type]) VALUES (2, N'CUSTOMERS', 5, 1, N'USER_ROLE')
INSERT [dbo].[Role] ([RoleID], [Name], [Order], [Status], [type]) VALUES (3, N'SALE', 4, 1, N'USER_ROLE')
INSERT [dbo].[Role] ([RoleID], [Name], [Order], [Status], [type]) VALUES (4, N'ADMIN', 1, 1, N'USER_ROLE')
INSERT [dbo].[Role] ([RoleID], [Name], [Order], [Status], [type]) VALUES (5, N'MARKETING', 3, 1, N'USER_ROLE')
SET IDENTITY_INSERT [dbo].[Role] OFF
GO
INSERT [dbo].[RolePermissionRequest] ([RoleID], [PermissionRequestID]) VALUES (2, 9)
INSERT [dbo].[RolePermissionRequest] ([RoleID], [PermissionRequestID]) VALUES (1, 6)
INSERT [dbo].[RolePermissionRequest] ([RoleID], [PermissionRequestID]) VALUES (1, 7)
INSERT [dbo].[RolePermissionRequest] ([RoleID], [PermissionRequestID]) VALUES (1, 8)
INSERT [dbo].[RolePermissionRequest] ([RoleID], [PermissionRequestID]) VALUES (1, 9)
INSERT [dbo].[RolePermissionRequest] ([RoleID], [PermissionRequestID]) VALUES (1, 12)
INSERT [dbo].[RolePermissionRequest] ([RoleID], [PermissionRequestID]) VALUES (1, 13)
INSERT [dbo].[RolePermissionRequest] ([RoleID], [PermissionRequestID]) VALUES (1, 14)
INSERT [dbo].[RolePermissionRequest] ([RoleID], [PermissionRequestID]) VALUES (1, 15)
GO
SET IDENTITY_INSERT [dbo].[Account] ON 

INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (1, N'Kristian', N'Kirtlan', N'kkirtlan0@un.org', N'default-account-profile-picture-7.jpg', 2, CAST(284.00 AS Decimal(15, 2)), CAST(N'2020-06-06T16:11:01.000' AS DateTime), CAST(N'2021-07-29T09:12:44.000' AS DateTime), N'5056135636', N'228 Sunnyside Point', 1)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (2, N'Gayla', N'Templeton', N'gtempleton1@oaic.gov.au', N'default-account-profile-picture-7.jpg', 3, CAST(69.00 AS Decimal(15, 2)), CAST(N'2019-12-07T14:37:58.000' AS DateTime), CAST(N'2019-12-10T19:45:14.000' AS DateTime), N'6726911033', N'2 Prentice Circle', 1)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (3, N'Prentice', N'Pidgin', N'ppidgin2@unesco.org', N'default-account-profile-picture-7.jpg', 2, CAST(409.00 AS Decimal(15, 2)), CAST(N'2018-11-20T20:14:32.000' AS DateTime), CAST(N'2019-01-15T04:20:57.000' AS DateTime), N'1737635200', N'4 Karstens Parkway', 1)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (4, N'Pebrook', N'Smaleman', N'psmaleman3@nsw.gov.au', N'default-account-profile-picture-7.jpg', 3, CAST(239.00 AS Decimal(15, 2)), CAST(N'2021-01-07T08:09:17.000' AS DateTime), CAST(N'2018-12-14T21:38:07.000' AS DateTime), N'9424540253', N'858 2nd Avenue', 1)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (5, N'Rebeca', N'Greeding', N'rgreeding4@bandcamp.com', N'default-account-profile-picture-7.jpg', 4, CAST(439.00 AS Decimal(15, 2)), CAST(N'2018-11-13T22:58:33.000' AS DateTime), CAST(N'2019-08-25T02:30:18.000' AS DateTime), N'7762675581', N'104 Forest Run Trail', 1)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (6, N'Nadya', N'Sharpley', N'nsharpley5@seattletimes.com', N'default-account-profile-picture-7.jpg', 2, CAST(394.00 AS Decimal(15, 2)), CAST(N'2018-09-09T23:32:36.000' AS DateTime), CAST(N'2020-03-03T15:31:34.000' AS DateTime), N'2504983052', N'74793 Crowley Circle', 1)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (7, N'Charmane', N'Bracken', N'cbracken6@sina.com.cn', N'default-account-profile-picture-7.jpg', 4, CAST(269.00 AS Decimal(15, 2)), CAST(N'2021-11-11T01:40:27.000' AS DateTime), CAST(N'2021-01-02T23:21:43.000' AS DateTime), N'3924821034', N'68856 Clyde Gallagher Trail', 0)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (8, N'Gennifer', N'Huniwall', N'ghuniwall7@cnbc.com', N'default-account-profile-picture-7.jpg', 5, CAST(73.00 AS Decimal(15, 2)), CAST(N'2021-11-02T06:20:25.000' AS DateTime), CAST(N'2019-01-27T00:51:59.000' AS DateTime), N'5028342556', N'6829 Kensington Avenue', 0)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (9, N'Nettie', N'Jepps', N'giaovien1@gmail.com', N'default-account-profile-picture-7.jpg', 1, CAST(236.00 AS Decimal(15, 2)), CAST(N'2019-01-03T09:51:35.000' AS DateTime), CAST(N'2020-11-20T08:27:53.000' AS DateTime), N'1961244376', N'24232 Graedel Circle', 1)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (10, N'Dore', N'Haymes', N'giaovien2@gmail.com', N'default-account-profile-picture-7.jpg', 1, CAST(449.00 AS Decimal(15, 2)), CAST(N'2021-05-25T20:48:55.000' AS DateTime), CAST(N'2021-03-06T16:47:17.000' AS DateTime), N'7587185601', N'5952 Melby Parkway', 0)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (11, N'Võ', N'Tài', N'tai@gmail.com', N'xiaomi-mi-gaming-laptop-stock-4k-nx-1920x1080.jpg', 2, CAST(75.00 AS Decimal(15, 2)), CAST(N'2010-04-25T00:00:00.000' AS DateTime), CAST(N'2022-06-03T00:42:55.267' AS DateTime), N'1234567890', N'4 Lighthouse Bay Circle', 1)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (12, N'Hà', N'Nam', N'nam@gmail.com', N'default-account-profile-picture-8.jpg', 4, CAST(32.00 AS Decimal(15, 2)), CAST(N'2022-04-10T00:00:00.000' AS DateTime), CAST(N'2022-06-10T20:43:01.937' AS DateTime), N'123123123', N'9 Lighthouse Bay Circle', 1)
INSERT [dbo].[Account] ([AccountID], [FirstName], [LastName], [Email], [ProfilePictureUrl], [RoleID], [Balance], [CreatedTime], [ModifiedTime], [Phone], [Address], [Gender]) VALUES (13, N'tai', N'123', N'votientai30@gmail.com', N'default-account-profile-picture-7.jpg', 2, CAST(10000.00 AS Decimal(15, 2)), CAST(N'2022-06-01T14:07:15.497' AS DateTime), CAST(N'2022-06-01T14:15:22.763' AS DateTime), N'123', N'', 1)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (1, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (2, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (3, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (4, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (5, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (6, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (7, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (8, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (9, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (10, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (11, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (12, N'111111')
INSERT [dbo].[Password] ([AccountID], [PasswordHash]) VALUES (13, N'111111')
GO
SET IDENTITY_INSERT [dbo].[SliderCollection] ON 

INSERT [dbo].[SliderCollection] ([SliderCollectionID], [Name], [CreatedTime], [UpdatedTime], [Order], [OwnerID], [Status]) VALUES (1, N'Software Engineer', CAST(N'2019-10-30T23:33:42.380' AS DateTime), CAST(N'2021-11-17T14:55:48.000' AS DateTime), 1, 5, 1)
INSERT [dbo].[SliderCollection] ([SliderCollectionID], [Name], [CreatedTime], [UpdatedTime], [Order], [OwnerID], [Status]) VALUES (2, N'Architecture', CAST(N'2020-03-05T18:52:16.610' AS DateTime), CAST(N'2010-06-03T05:29:35.690' AS DateTime), 2, 8, 0)
SET IDENTITY_INSERT [dbo].[SliderCollection] OFF
GO
SET IDENTITY_INSERT [dbo].[Blog] ON 

INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (1, N'Save Hundreds on Refurb iPhones and Apple Watches Today Only at Woot', N'If you can live with some dings and scrapes, these are some of the best prices you''ll find on Apple devices out there at the moment.', N'<p><br />
From phones to earbuds to smart watches, Apple devices consistently score top marks in our reviews, frequently&nbsp; claiming the top spot in our best device lists .&nbsp; Sleek and simple with consistently impressive performance, Apple devices really only have one major drawback: They&#39;re pricey.&nbsp; Apple almost never drops its prices, which can make finding its products at a bargain a challenge.&nbsp; If you don&#39;t mind a preowned device, we&#39;ve got a deal you won&#39;t want to miss.&nbsp; Today only, Woot has a&nbsp; massive selection of refurbished Apple Watches and iPhones &nbsp;on sale, and you can save hundreds compared with what it would cost you to buy from Apple directly.</p>

<p>All the devices you&#39;ll find at this sale are &quot;scratch and dent&quot;-grade refurbs.&nbsp; According to Woot &nbsp;, that means that you can expect these items to exhibit moderate signs of wear and tear, but they have all been thoroughly inspected to make sure they&#39;re in full working condition.&nbsp; Though none of the latest iPhone 13 or Apple Watch Series 7 models are on sale, many of these previous-generation devices are a great buy.&nbsp;</p>

<p>With 5G capabilities, an OLED display and equipped with Apple&#39;s A14 Bionic chip, the &nbsp; iPhone 12 is still an excellent phone &nbsp;that&#39;s more than sufficient for most users.&nbsp; It&#39;s&nbsp; a great value starting at just $480 , which is $219 less than buying new from Apple directly.&nbsp; And if you want an even more affordable option, there are models as old as the &nbsp; iPhone 7 &nbsp;available, with prices starting as low as $120.</p>

<p>If you&#39;re in the market for a smartwatch, the &nbsp; Apple Watch Series 6 &nbsp;, which you can pick up for as low as $250, offers&nbsp; many of the same features as the upgraded Series 7 &nbsp;but will save you $149.&nbsp; Or, you can save on Apple&#39;s budget-friendly alternative, the &nbsp; Apple WatchSE .&nbsp; This Apple Watch &quot;lite&quot; isn&#39;t equipped with the same advanced health sensors as the &nbsp; Apple Watch Series 6 , but it&#39;s still packed with plenty of impressive features and you can &nbsp; pick one up for as low as $170 today , which is $109 less than it would cost you from Apple directly.</p>

<p>Apple deals are slim, and these are some of the best values you&#39;ll find right now.&nbsp; This Woot sale expires tonight at 9:59 pm PT (12:59 am ET), or when these refurbished models sell out, so we recommend acting sooner rather than later.</p>
', CAST(N'2022-06-03' AS Date), 12, 1, N'cat-2.jpg', 259)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (2, N'Johnny Depp Performs With Jeff Beck Ahead of Trial Verdict', N'The actor performed three songs during the musician''s concert at Sheffield City Hall', N'<p>Johnny Depp made a surprise appearance onstage during Jeff Beck &#39;s show in Sheffield last night (May 29).</p>

<p>beck was performing at Sheffield City Hall as part of his current UK and European headline tour , which is set to continue at the Royal Albert Hall in London this evening (May 30).</p>

<p>Depp returned to the UK as he awaits the verdict in his defamation trial against ex-wife Amber Heard. Closing arguments were heard in court last Friday (May 27), with a decision expected early this week.</p>

<p>The actor &ndash; who formed the band Hollywood Vampires with Alice Cooper and Joe Perry in 2012 &ndash; played guitar for three songs in Beck&#39;s set last night, including the pair&#39;s joint 2020 cover of John Lennon&#39;s &#39;Isolation.&#39;</p>

<p>&nbsp;</p>

<p>According to Deadline, Depp could be due to appear at Beck&#39;s next two concerts at London&#39;s Royal Albert Hall. The latter&#39;s 2022 tour also includes dates in Gateshead (June 2), Glasgow (3), Manchester (4), Birmingham (6) and York (7).</p>

<p>It was reported last summer that the duo working on new material together for Beck&#39;s next studio album, which would follow on from 2016&#39;s &#39;Loud Hailer&#39;.</p>

<p>Depp is suing Amber Heard for defamation over a 2018 washington post op-ed in which she wrote about being a survivor of domestic violence. She did not mention Depp by name in the piece, but his lawyers argued that it implied she was abused by him during their marriage.</p>

<p>Heard has also filed a counterclaim against her ex-husband, alleging that Depp has created a smear campaign against her.</p>

<p>Should the jury conclude that Heard defamed Depp, she will be ordered to pay him compensation for loss of earnings. He is seeking a sum of $50million (&pound;39.5m).</p>
', CAST(N'2022-06-03' AS Date), 12, 1, N'1_5924332.jpg', 2507)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (3, N'S''pore worker wants to quit US$2,480 job because he has nothing to do in the office', N'Don''t overwork yourself, buddy.', N'<p>A worker based in Singapore has raised more than a few eyebrows after posting up his thoughts about his current job and the fact that he&#39;s thinking of quitting it because he has too little to do in the office.</p>

<p>On May 26, 2022, the man named Kenji Ong took to a&nbsp; Singaporean Facebook group &nbsp;to lament his current job, which pays him S$3,400 (US$2,480) monthly.&nbsp; In the post, he said that he wanted to leave his position because he had almost nothing substantial to do on a daily basis.</p>

<p>Ong explained that he was frustrated with the fact that he would show up to the office only to turn on his computer and deal with 20 to 30 emails before calling it quits for the day.</p>

<p>He also explained that it had become even more unbearable after his company had begun requesting employees to return to the office following the relaxing of COVID-19 measures across the country.</p>

<p>&quot;When I was working from home, I could complete my work in two to three hours, and then I mostly did my own things and it became easy to kill time at home,&quot; he said in the post.</p>

<p>But forced now to return to the office, he complained that it was harder to kill time due to his boss being seated directly behind him, and that his current work routine now involves him pretending to type into Microsoft Excel most of the time, and then leaving his job at 5.30 pm on the dot.</p>

<p>Additionally, Ong also revealed that several of his colleagues were also going through the same predicament, with some of them repeatedly looking at emails, daydreaming, or spending more time in the bathroom as a result.</p>

<p>He said that all in all, his job was relatively comfortable and fuss-free, although he had for months since returning to the office felt that there was something &quot;missing&quot;, and that he had become tired and lethargic after spending his working days in this way.</p>

<p>&quot;Pretending to be busy while I have have nothing to do is unbearable,&quot; Ong said. &quot;Sometimes I really just want to scream &#39;Aaah! I&#39;m so bored!&#39;.&quot;</p>
', CAST(N'2022-06-25' AS Date), 12, 0, N'', 2670)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (4, N'Apple''s 24-inch iMac puts the fun back in family computer', N'When Apple introduced its 27-inch iMac last year, I had a strong feeling the company was attempting to bring back the family computer.', N'<p>When Apple introduced its&nbsp; 27-inch iMac last year , I had a strong feeling the company was attempting to bring back the family computer.&nbsp; And this year, it looks like Apple actually delivered.</p>

<p>The new, colorful 24-inch iMac , which starts at US$1,200 and begins shipping May 21, is a clear callback to the old-school, colorful iMac G3 &mdash; Apple&#39;s original family computer that sat in most people&#39;s living rooms throughout the late &#39;90s and early 2000s.&nbsp; It acted as the central hub to the internet for all members of the household, both young and old.</p>

<p>Since then, the iMac has evolved into a machine that represents power and productivity, with a boring, all-white industrial design to match.&nbsp; You&#39;re more likely to see it tucked away in an office cubicle in corporate spaces than situated on a messy desk in someone&#39;s cozy living room.</p>

<p>But the new iMac finally breaks that pattern, moving back into the family room using color &nbsp; <em> and </em> &nbsp;a few other notable upgrades.</p>

<p>In addition to being offered in seven different colors, the 24-inch iMac now ditches Intel processors for&nbsp; Apple&#39;s own M1 chip, &nbsp;allowing for a super-thin, sleek, and lightweight form factor.&nbsp; Throw in a 1080p camera for all those video chats, a 4.5K Retina display for binging Netflix, Touch ID built into the Magic Keyboard to easily switch between user profiles and input passwords, to name a few, and you&#39;ve got yourself the ideal at-home iMac.</p>

<p>&nbsp;</p>
', CAST(N'2022-06-03' AS Date), 12, 1, N'imac.jpg', 1190)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (5, N'Transformations: Editing Life in a Day', N'Running from the end of May to mid-June, UCL''s Reimagine series offered an exciting collection of short courses, master classes and workshops that encouraged participants to reimagine their future. ', N'<p>One of the incredible events of the programme was &lsquo;Transformations: Moving Image Storytelling&rsquo;, a Q&amp;A event with film industry professionals. The event centred around&nbsp;<em>Life In A Day</em>, a documentary that captures the day of July 25th, 2020 from a global angle. The film followed the&nbsp;<em>Life In A Day&nbsp;</em>that was created in 2010, a documentary of similar form. Over 300,000 videos from 192 countries were submitted to the project, connecting themes of love, death, hope and more. The event aimed to capture the experience of editing this intriguing, captivating film.</p>

<p>The panel was chaired by Kate Stonehill. Kate is an award-winning director and cinematographer whose work has screened internationally at film festivals and galleries including the BFI London Film Festival, Sheffield Doc/Fest, AFI Docs, and DOC NYC. Kate was joined by Mdhamiri Nkemi, a film editor whose work has won awards at festivals such as Sundance, Berlinale, TIFF, SXSW and the London Film Festival, and Nse Asuquo, editor of&nbsp;<em>The Stuart Hall Project</em>,&nbsp;<em>Jazz Ambassadors&nbsp;</em>and&nbsp;<em>House of My Fathers&nbsp;</em>and nominee for the Jules Wright Prize for Female Creative Technicians in 2016. Both Mdhamiri and Nse were involved with the making and editing of&nbsp;<em>Life in a Day</em>.</p>

<p>Kate began by asking what Mdhamiri and Nse were looking to capture within the final film, one that, she notes, was created in a &lsquo;transformative year.&rsquo; Nse reflected on how open she was going into the experience and the fact she had no expectations, whilst Mdhamiri knew the film would have an &lsquo;extra layer of impact&rsquo; following the international experience of the COVID-19 pandemic. Mdhamiri spoke of how, in addition to COVID, the wider conversations of the time framed the video submissions they received, and how the recent death of George Floyd became an &lsquo;important thing to talk about&rsquo; in the film. It was important, too, that the film did not feel like a collection of films categorized as a list, but that they instead worked to capture &lsquo;the human experience in its essence and have all [the] themes feed into that naturally.&rsquo; Kate believes the film strikes a balance between &lsquo;being alive to the time in which it was made&rsquo; and the mundane events of ordinary lives that &lsquo;could have been shot at any time.&rsquo; Although her world felt very small whilst being confined to her home during the pandemic, the film gave Kate a window into life &lsquo;on the other side of the world.&rsquo;&nbsp;</p>

<p>The discussion moved onto reflecting on the experience of editing a film without the element of hindsight and instead editing global experiences that were happening in a moment that Nse and Mdhamiri were themselves very much a part of, and whether this was a challenge. Nse agreed that it was challenging, as director Kevin Macdonald didn&rsquo;t want the film to be a &lsquo;YouTube thing&rsquo; but rather wanted people to express what was going on in their lives. Nse also touched on the issue around how to portray the Black Lives Matter protests, as a lot of the footage was of people talking about the protests without being &lsquo;intimately connected&rsquo; to it; they entered the theme emotionally through a personal story of someone who was directly affected by the protests.&nbsp;</p>

<p>&nbsp;</p>
', CAST(N'2022-06-03' AS Date), 12, 1, N'Screenshot 2022-06-03 115348.jpg', 1384)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (6, N'Cute computer mouse also has a tiny computer in it', N'Mouse reception.', N'<p>&nbsp;</p>

<p>Yeah, so you might have a fancy keyboard with a screen on it.</p>

<p>But we bet it doesn&#39;t hold a candle to this computer mouse, which hosts a whole, functional computer inside of its housing, complete with keyboard and screen.</p>

<p>It&#39;s a DIY project by YouTuber Electronic Grenade, who also managed to fit an entire gaming system&nbsp; inside a controller &nbsp;a while back.&nbsp;</p>

<p>The mouse&#39;s housing is 3D printed, while the computer itself is a Raspberry Pi Zero, connected to a bluetooth mini keyboard, a 1.5-inch LCD screen, and uses the parts from a wired mouse.&nbsp; And for the most part, it works.</p>

<p>&quot;Even though the screen is attached to the mouse, the sensitivity of the mouse actually makes it not that hard to follow along with what&#39;s happening on the screen,&quot; Electronic Grenade explains in the video.</p>

<p>Sure, the Raspberry Pi sometimes freezes up, the keyboard is cramped and in an awkward position, but you can play&nbsp;&nbsp; <em> Minecraft </em> &nbsp;on it (for about 15 seconds before it freezes.)</p>

<p>&quot;Even though this definitely isn&#39;t the most practical thing I&#39;ve built, it&#39;s still one of my favourites,&quot; Electronic Grenade added.</p>

<p>It&#39;s not the only creative use of a Raspberry Pi recently, with a&nbsp; swipe card-powered jukebox &nbsp;and an&nbsp; e-ink media player &nbsp;catching our eye as of late.</p>

<p>&nbsp;</p>
', CAST(N'2022-06-03' AS Date), 12, 1, N'hero-image.fill.size_1200x1200.v1614271136.png', 883)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (8, N'huhu', N'haha', N'<p>dasdasdhaha</p>
', CAST(N'2022-06-25' AS Date), 12, 0, N'', 0)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (9, N'dasd', N'asd', N'<p>dsad</p>
', CAST(N'2022-06-22' AS Date), 12, 0, N'', 0)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (10, N'dasd', N'asd', N'<p>dsad</p>
', CAST(N'2022-06-22' AS Date), 12, 0, N'', 0)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (11, N'update', N'update', N'<p>upupdarw</p>

<p>&nbsp;</p>
', CAST(N'2022-06-26' AS Date), 12, 0, N'', 1)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (14, N'asdas', N'dasdas', N'<p>dasdasd</p>
', CAST(N'2022-06-26' AS Date), 12, 0, N'wallpaperflare.com_wallpaper (1).jpg', 0)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (15, N'dasdas', N'dasdasdasd', N'<p>dasdasd</p>
', CAST(N'2022-06-26' AS Date), 12, 0, N'wallpaperflare.com_wallpaper.jpg', 0)
INSERT [dbo].[Blog] ([BlogID], [Title], [Description], [Content], [CreatedDate], [AuthorID], [Display], [ThumbnailUrl], [NumberOfView]) VALUES (16, N'new post', N'new post', N'<p>new new new new</p>
', CAST(N'2022-06-26' AS Date), 12, 0, N'', 0)
SET IDENTITY_INSERT [dbo].[Blog] OFF
GO
SET IDENTITY_INSERT [dbo].[Course] ON 

INSERT [dbo].[Course] ([CourseID], [Name], [Description], [InstructorID], [TinyPictureUrl], [ThumbnailUrl], [CreatedDate], [ModifiedDate], [Price], [Status], [VideoIntroduce], [AboutCourse]) VALUES (1, N'Python for Everybody', N'This Specialization builds on the success of the Python for Everybody course and will introduce fundamental programming concepts including data structures, networked application program interfaces, and databases, using the Python programming language. ', 9, N'https://wallpaperaccess.com/full/796849.jpg', N'https://wallpaperaccess.com/full/796849.jpg', CAST(N'1958-09-24' AS Date), CAST(N'1997-11-14' AS Date), CAST(67.00 AS Decimal(15, 2)), 1, N'https://www.youtube.com/embed/viHILXVY_eU', N'Whether you’ve been tinkering with social media platforms for your
                business already or are completely new to the field of digital marketing, you’ve come to the right
                place. This six-course program, developed by digital marketing experts at Aptly together with Meta
                marketers, includes an industry-relevant curriculum designed to prepare you for an entry-level role in
                social media marketing.
                <br>
                <br>
                After an introduction to digital marketing and major social media platforms, you’ll learn to establish
                an online presence, create posts, build a following, and manage your social media accounts. You’ll
                develop skills in creating and managing advertising campaigns in social media and learn to evaluate the
                results of your marketing efforts.
                <br>
                <br>
                Upon successful completion of the program, you’ll earn both the Coursera and the Meta Digital Marketing
                Associate Certification, proving your skills in social media marketing and in the use of Meta Ads
                Manager.
                <br>
                <br>
                Once you earn your Meta Certification, you’ll get exclusive access to the new Meta Career Programs Job
                Board—a job search platform that connects Meta Certified professionals with 200+ top employers who have
                committed to sourcing talent through its certification programs. We’ll provide you with the link once
                you’ve completed all the courses and passed the exam.')
INSERT [dbo].[Course] ([CourseID], [Name], [Description], [InstructorID], [TinyPictureUrl], [ThumbnailUrl], [CreatedDate], [ModifiedDate], [Price], [Status], [VideoIntroduce], [AboutCourse]) VALUES (2, N'Deep Learning', N'The Deep Learning Specialization is a foundational program that will help you understand the capabilities, challenges, and consequences of deep learning and prepare you to participate in the development of leading-edge AI technology.', 10, N'https://wallpaperaccess.com/full/1728956.jpg', N'https://wallpaperaccess.com/full/1728956.jpg', CAST(N'1988-02-16' AS Date), CAST(N'1979-11-12' AS Date), CAST(54.00 AS Decimal(15, 2)), 1, N'https://www.youtube.com/embed/viHILXVY_eU', N'Whether you’ve been tinkering with social media platforms for your
                business already or are completely new to the field of digital marketing, you’ve come to the right
                place. This six-course program, developed by digital marketing experts at Aptly together with Meta
                marketers, includes an industry-relevant curriculum designed to prepare you for an entry-level role in
                social media marketing.
                <br>
                <br>
                After an introduction to digital marketing and major social media platforms, you’ll learn to establish
                an online presence, create posts, build a following, and manage your social media accounts. You’ll
                develop skills in creating and managing advertising campaigns in social media and learn to evaluate the
                results of your marketing efforts.
                <br>
                <br>
                Upon successful completion of the program, you’ll earn both the Coursera and the Meta Digital Marketing
                Associate Certification, proving your skills in social media marketing and in the use of Meta Ads
                Manager.
                <br>
                <br>
                Once you earn your Meta Certification, you’ll get exclusive access to the new Meta Career Programs Job
                Board—a job search platform that connects Meta Certified professionals with 200+ top employers who have
                committed to sourcing talent through its certification programs. We’ll provide you with the link once
                you’ve completed all the courses and passed the exam.')
INSERT [dbo].[Course] ([CourseID], [Name], [Description], [InstructorID], [TinyPictureUrl], [ThumbnailUrl], [CreatedDate], [ModifiedDate], [Price], [Status], [VideoIntroduce], [AboutCourse]) VALUES (3, N'Bachelor of Applied Arts and Sciences', N'The fastest, most affordable way to complete your bachelor’s degree online from a top 9 research school in Texas while leveraging your previous college or military credits.', 9, N'https://wallpaperaccess.com/full/2970443.jpg', N'https://wallpaperaccess.com/full/2970443.jpg', CAST(N'1995-01-05' AS Date), CAST(N'2001-09-14' AS Date), CAST(89.00 AS Decimal(15, 2)), 1, N'https://www.youtube.com/embed/viHILXVY_eU', N'Whether you’ve been tinkering with social media platforms for your
                business already or are completely new to the field of digital marketing, you’ve come to the right
                place. This six-course program, developed by digital marketing experts at Aptly together with Meta
                marketers, includes an industry-relevant curriculum designed to prepare you for an entry-level role in
                social media marketing.
                <br>
                <br>
                After an introduction to digital marketing and major social media platforms, you’ll learn to establish
                an online presence, create posts, build a following, and manage your social media accounts. You’ll
                develop skills in creating and managing advertising campaigns in social media and learn to evaluate the
                results of your marketing efforts.
                <br>
                <br>
                Upon successful completion of the program, you’ll earn both the Coursera and the Meta Digital Marketing
                Associate Certification, proving your skills in social media marketing and in the use of Meta Ads
                Manager.
                <br>
                <br>
                Once you earn your Meta Certification, you’ll get exclusive access to the new Meta Career Programs Job
                Board—a job search platform that connects Meta Certified professionals with 200+ top employers who have
                committed to sourcing talent through its certification programs. We’ll provide you with the link once
                you’ve completed all the courses and passed the exam.')
INSERT [dbo].[Course] ([CourseID], [Name], [Description], [InstructorID], [TinyPictureUrl], [ThumbnailUrl], [CreatedDate], [ModifiedDate], [Price], [Status], [VideoIntroduce], [AboutCourse]) VALUES (4, N'Javascript for Beginners Learn by Doing Practical Exercises', N'JavaScript for Beginners : Work closely with me doing exercises & learn more. I make Javascript easy for you guaranteed.', 10, N'https://c.wallhere.com/photos/cc/ce/1920x1080_px_JavaScript_minimalism-1473283.jpg!d', N'https://c.wallhere.com/photos/cc/ce/1920x1080_px_JavaScript_minimalism-1473283.jpg!d', CAST(N'1993-04-26' AS Date), CAST(N'1968-07-02' AS Date), CAST(76.00 AS Decimal(15, 2)), 1, N'https://www.youtube.com/embed/viHILXVY_eU', N'Whether you’ve been tinkering with social media platforms for your
                business already or are completely new to the field of digital marketing, you’ve come to the right
                place. This six-course program, developed by digital marketing experts at Aptly together with Meta
                marketers, includes an industry-relevant curriculum designed to prepare you for an entry-level role in
                social media marketing.
                <br>
                <br>
                After an introduction to digital marketing and major social media platforms, you’ll learn to establish
                an online presence, create posts, build a following, and manage your social media accounts. You’ll
                develop skills in creating and managing advertising campaigns in social media and learn to evaluate the
                results of your marketing efforts.
                <br>
                <br>
                Upon successful completion of the program, you’ll earn both the Coursera and the Meta Digital Marketing
                Associate Certification, proving your skills in social media marketing and in the use of Meta Ads
                Manager.
                <br>
                <br>
                Once you earn your Meta Certification, you’ll get exclusive access to the new Meta Career Programs Job
                Board—a job search platform that connects Meta Certified professionals with 200+ top employers who have
                committed to sourcing talent through its certification programs. We’ll provide you with the link once
                you’ve completed all the courses and passed the exam.')
INSERT [dbo].[Course] ([CourseID], [Name], [Description], [InstructorID], [TinyPictureUrl], [ThumbnailUrl], [CreatedDate], [ModifiedDate], [Price], [Status], [VideoIntroduce], [AboutCourse]) VALUES (5, N'Learn Python: The Complete Python Programming Course
', N'Learn A-Z everything about Python, from the basics, to advanced topics like Python GUI, Python Data Analysis, and more!
', 9, N'https://images.hdqwalls.com/wallpapers/python-logo-4k-i6.jpg', N'https://images.hdqwalls.com/wallpapers/python-logo-4k-i6.jpg', CAST(N'2002-03-20' AS Date), CAST(N'1957-09-03' AS Date), CAST(55.00 AS Decimal(15, 2)), 1, N'https://www.youtube.com/embed/viHILXVY_eU', N'Whether you’ve been tinkering with social media platforms for your
                business already or are completely new to the field of digital marketing, you’ve come to the right
                place. This six-course program, developed by digital marketing experts at Aptly together with Meta
                marketers, includes an industry-relevant curriculum designed to prepare you for an entry-level role in
                social media marketing.
                <br>
                <br>
                After an introduction to digital marketing and major social media platforms, you’ll learn to establish
                an online presence, create posts, build a following, and manage your social media accounts. You’ll
                develop skills in creating and managing advertising campaigns in social media and learn to evaluate the
                results of your marketing efforts.
                <br>
                <br>
                Upon successful completion of the program, you’ll earn both the Coursera and the Meta Digital Marketing
                Associate Certification, proving your skills in social media marketing and in the use of Meta Ads
                Manager.
                <br>
                <br>
                Once you earn your Meta Certification, you’ll get exclusive access to the new Meta Career Programs Job
                Board—a job search platform that connects Meta Certified professionals with 200+ top employers who have
                committed to sourcing talent through its certification programs. We’ll provide you with the link once
                you’ve completed all the courses and passed the exam.')
INSERT [dbo].[Course] ([CourseID], [Name], [Description], [InstructorID], [TinyPictureUrl], [ThumbnailUrl], [CreatedDate], [ModifiedDate], [Price], [Status], [VideoIntroduce], [AboutCourse]) VALUES (6, N'Learning Python for Data Analysis and Visualization
', N'Learn python and how to use it to analyze,visualize and present data. Includes tons of sample code and hours of video!
', 10, N'https://wallpapercave.com/wp/wp9904460.png', N'https://wallpapercave.com/wp/wp9904460.png', CAST(N'1954-12-03' AS Date), CAST(N'1997-10-26' AS Date), CAST(90.00 AS Decimal(15, 2)), 1, N'https://www.youtube.com/embed/viHILXVY_eU', N'Whether you’ve been tinkering with social media platforms for your
                business already or are completely new to the field of digital marketing, you’ve come to the right
                place. This six-course program, developed by digital marketing experts at Aptly together with Meta
                marketers, includes an industry-relevant curriculum designed to prepare you for an entry-level role in
                social media marketing.
                <br>
                <br>
                After an introduction to digital marketing and major social media platforms, you’ll learn to establish
                an online presence, create posts, build a following, and manage your social media accounts. You’ll
                develop skills in creating and managing advertising campaigns in social media and learn to evaluate the
                results of your marketing efforts.
                <br>
                <br>
                Upon successful completion of the program, you’ll earn both the Coursera and the Meta Digital Marketing
                Associate Certification, proving your skills in social media marketing and in the use of Meta Ads
                Manager.
                <br>
                <br>
                Once you earn your Meta Certification, you’ll get exclusive access to the new Meta Career Programs Job
                Board—a job search platform that connects Meta Certified professionals with 200+ top employers who have
                committed to sourcing talent through its certification programs. We’ll provide you with the link once
                you’ve completed all the courses and passed the exam.')
INSERT [dbo].[Course] ([CourseID], [Name], [Description], [InstructorID], [TinyPictureUrl], [ThumbnailUrl], [CreatedDate], [ModifiedDate], [Price], [Status], [VideoIntroduce], [AboutCourse]) VALUES (7, N'Photography for Kids: Project-Based Beginner Photography
', N'Photography for Kids: Project-Based Beginner Photography
', 10, N'https://wallpaperaccess.com/full/1209559.jpg', N'https://wallpaperaccess.com/full/1209559.jpg', CAST(N'2009-05-09' AS Date), CAST(N'1964-02-07' AS Date), CAST(0.00 AS Decimal(15, 2)), 1, N'https://www.youtube.com/embed/viHILXVY_eU', N'Whether you’ve been tinkering with social media platforms for your
                business already or are completely new to the field of digital marketing, you’ve come to the right
                place. This six-course program, developed by digital marketing experts at Aptly together with Meta
                marketers, includes an industry-relevant curriculum designed to prepare you for an entry-level role in
                social media marketing.
                <br>
                <br>
                After an introduction to digital marketing and major social media platforms, you’ll learn to establish
                an online presence, create posts, build a following, and manage your social media accounts. You’ll
                develop skills in creating and managing advertising campaigns in social media and learn to evaluate the
                results of your marketing efforts.
                <br>
                <br>
                Upon successful completion of the program, you’ll earn both the Coursera and the Meta Digital Marketing
                Associate Certification, proving your skills in social media marketing and in the use of Meta Ads
                Manager.
                <br>
                <br>
                Once you earn your Meta Certification, you’ll get exclusive access to the new Meta Career Programs Job
                Board—a job search platform that connects Meta Certified professionals with 200+ top employers who have
                committed to sourcing talent through its certification programs. We’ll provide you with the link once
                you’ve completed all the courses and passed the exam.')
INSERT [dbo].[Course] ([CourseID], [Name], [Description], [InstructorID], [TinyPictureUrl], [ThumbnailUrl], [CreatedDate], [ModifiedDate], [Price], [Status], [VideoIntroduce], [AboutCourse]) VALUES (8, N'Photoshop Master Course: From Beginner to Photoshop Pro
', N'This Adobe Photoshop Beginner Course will teach a Beginner Photoshop user all essentials of Adobe Photoshop CC
', 9, N'https://media5.sgp1.digitaloceanspaces.com/wp-content/uploads/2021/10/13115628/Adobe-Photoshop-Wallpapers.jpg', N'https://media5.sgp1.digitaloceanspaces.com/wp-content/uploads/2021/10/13115628/Adobe-Photoshop-Wallpapers.jpg', CAST(N'1954-09-07' AS Date), CAST(N'1989-07-14' AS Date), CAST(0.00 AS Decimal(15, 2)), 1, N'https://www.youtube.com/embed/viHILXVY_eU', N'Whether you’ve been tinkering with social media platforms for your
                business already or are completely new to the field of digital marketing, you’ve come to the right
                place. This six-course program, developed by digital marketing experts at Aptly together with Meta
                marketers, includes an industry-relevant curriculum designed to prepare you for an entry-level role in
                social media marketing.
                <br>
                <br>
                After an introduction to digital marketing and major social media platforms, you’ll learn to establish
                an online presence, create posts, build a following, and manage your social media accounts. You’ll
                develop skills in creating and managing advertising campaigns in social media and learn to evaluate the
                results of your marketing efforts.
                <br>
                <br>
                Upon successful completion of the program, you’ll earn both the Coursera and the Meta Digital Marketing
                Associate Certification, proving your skills in social media marketing and in the use of Meta Ads
                Manager.
                <br>
                <br>
                Once you earn your Meta Certification, you’ll get exclusive access to the new Meta Career Programs Job
                Board—a job search platform that connects Meta Certified professionals with 200+ top employers who have
                committed to sourcing talent through its certification programs. We’ll provide you with the link once
                you’ve completed all the courses and passed the exam.')
INSERT [dbo].[Course] ([CourseID], [Name], [Description], [InstructorID], [TinyPictureUrl], [ThumbnailUrl], [CreatedDate], [ModifiedDate], [Price], [Status], [VideoIntroduce], [AboutCourse]) VALUES (9, N'Google Ads for Beginners
', N'Learn how to effectively use Google Ads to reach more customers online and grow your business.
', 10, N'https://wallpapercave.com/wp/wp10324532.jpg', N'https://wallpapercave.com/wp/wp10324532.jpg', CAST(N'1980-06-04' AS Date), CAST(N'2006-01-13' AS Date), CAST(0.00 AS Decimal(15, 2)), 1, N'https://www.youtube.com/embed/viHILXVY_eU', N'Whether you’ve been tinkering with social media platforms for your
                business already or are completely new to the field of digital marketing, you’ve come to the right
                place. This six-course program, developed by digital marketing experts at Aptly together with Meta
                marketers, includes an industry-relevant curriculum designed to prepare you for an entry-level role in
                social media marketing.
                <br>
                <br>
                After an introduction to digital marketing and major social media platforms, you’ll learn to establish
                an online presence, create posts, build a following, and manage your social media accounts. You’ll
                develop skills in creating and managing advertising campaigns in social media and learn to evaluate the
                results of your marketing efforts.
                <br>
                <br>
                Upon successful completion of the program, you’ll earn both the Coursera and the Meta Digital Marketing
                Associate Certification, proving your skills in social media marketing and in the use of Meta Ads
                Manager.
                <br>
                <br>
                Once you earn your Meta Certification, you’ll get exclusive access to the new Meta Career Programs Job
                Board—a job search platform that connects Meta Certified professionals with 200+ top employers who have
                committed to sourcing talent through its certification programs. We’ll provide you with the link once
                you’ve completed all the courses and passed the exam.')
SET IDENTITY_INSERT [dbo].[Course] OFF
GO
SET IDENTITY_INSERT [dbo].[LessonType] ON 

INSERT [dbo].[LessonType] ([LessonTypeID], [Name], [Order], [Status], [type]) VALUES (1, N'Video Lesson ', 2, 1, N'TYPE_LESSON')
INSERT [dbo].[LessonType] ([LessonTypeID], [Name], [Order], [Status], [type]) VALUES (2, N'Quiz Lesson', 1, 1, N'TYPE_LESSON')
INSERT [dbo].[LessonType] ([LessonTypeID], [Name], [Order], [Status], [type]) VALUES (4, N'xcvb', 2, 1, N'TYPE_LESSON')
SET IDENTITY_INSERT [dbo].[LessonType] OFF
GO

SET IDENTITY_INSERT [dbo].[SubLesson] ON 

INSERT [dbo].[SubLesson] ([SubLessonID], [CourseID], [Name]) VALUES (1, 1, N'Working with Data: Part 1')
INSERT [dbo].[SubLesson] ([SubLessonID], [CourseID], [Name]) VALUES (2, 1, N'Working with Data: Part 2')
INSERT [dbo].[SubLesson] ([SubLessonID], [CourseID], [Name]) VALUES (3, 1, N'Working with Data: Part 3')
INSERT [dbo].[SubLesson] ([SubLessonID], [CourseID], [Name]) VALUES (4, 1, N'Data Visualization')
INSERT [dbo].[SubLesson] ([SubLessonID], [CourseID], [Name]) VALUES (5, 2, N'Setup')
INSERT [dbo].[SubLesson] ([SubLessonID], [CourseID], [Name]) VALUES (6, 2, N'Learning Numpy')
INSERT [dbo].[SubLesson] ([SubLessonID], [CourseID], [Name]) VALUES (7, 2, N'Intro to Pandas')
INSERT [dbo].[SubLesson] ([SubLessonID], [CourseID], [Name]) VALUES (8, 3, N'Machine Learning')
INSERT [dbo].[SubLesson] ([SubLessonID], [CourseID], [Name]) VALUES (9, 3, N'Example Projects')
SET IDENTITY_INSERT [dbo].[SubLesson] OFF
GO

SET IDENTITY_INSERT [dbo].[Lesson] ON 

INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (1, 1, 1, N'Microsoft Excel files with Python', CAST(N'1986-08-25T00:25:55.480' AS DateTime), CAST(N'1953-09-11T15:48:01.100' AS DateTime), N'', N'', 1, 1, N'img/online-learning-1.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 1)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (2, 1, 1, N'HTML with Python', CAST(N'1962-06-15T00:30:47.750' AS DateTime), CAST(N'1982-09-12T11:08:06.830' AS DateTime), N'', N'', 2, 1, N'img/online-learning-2.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 1)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (3, 1, 1, N'Reading and Writing Text Files', CAST(N'1983-03-05T06:26:46.270' AS DateTime), CAST(N'1962-05-28T11:09:30.730' AS DateTime), N'', N'', 3, 1, N'img/online-learning-3.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 1)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (4, 1, 2, N'JSON with Python', CAST(N'1987-08-21T10:16:14.890' AS DateTime), NULL, N'', N'', 4, 1, NULL, CAST(N'2022-06-12T10:30:00.000' AS DateTime), 1)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (5, 1, 1, N'Merge on Index', CAST(N'2015-05-31T15:50:32.530' AS DateTime), CAST(N'2002-05-13T09:40:40.270' AS DateTime), N'', N'', 5, 1, N'img/online-learning-4.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 2)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (6, 1, 1, N'Combining DataFrames', CAST(N'2014-11-13T18:56:39.270' AS DateTime), CAST(N'1977-07-26T13:15:12.370' AS DateTime), N'', N'', 6, 1, N'img/online-learning-5.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 2)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (7, 1, 1, N'Duplicates in DataFrames', CAST(N'2021-03-23T05:10:04.600' AS DateTime), CAST(N'2006-03-02T21:18:40.570' AS DateTime), N'', N'', 7, 1, N'img/online-learning-6.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 2)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (8, 1, 2, N'Rename Index', CAST(N'2020-02-03T17:49:04.130' AS DateTime), CAST(N'1983-02-21T16:36:43.210' AS DateTime), N'', N'', 8, 1, NULL, CAST(N'2022-06-12T10:30:00.000' AS DateTime), 2)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (9, 1, 1, N'GroupBy on DataFrames', CAST(N'1997-07-16T22:34:02.950' AS DateTime), CAST(N'1985-09-16T16:35:06.220' AS DateTime), N'', N'', 9, 1, N'img/online-learning-7.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 3)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (10, 1, 1, N'GroupBy on Dict and Series', CAST(N'1963-12-17T13:00:06.850' AS DateTime), CAST(N'2006-01-28T17:23:49.560' AS DateTime), N'', N'', 10, 1, N'img/online-learning-8.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 3)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (11, 1, 1, N'Splitting Applying and Combining', CAST(N'2018-05-14T18:28:29.410' AS DateTime), CAST(N'1989-05-27T10:30:56.820' AS DateTime), N'', N'', 11, 1, N'img/online-learning-9.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 3)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (12, 1, 2, N'Cross Tabulation', CAST(N'1998-02-28T00:51:30.630' AS DateTime), CAST(N'2000-03-15T02:46:46.800' AS DateTime), N'', N'', 12, 1, NULL, CAST(N'2022-06-12T10:30:00.000' AS DateTime), 3)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (13, 1, 1, N'Installing Seaborn', CAST(N'2017-04-07T18:11:51.570' AS DateTime), CAST(N'1983-01-11T19:41:31.560' AS DateTime), N'', N'', 13, 1, N'img/online-learning-10.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 4)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (14, 1, 1, N'Histograms', CAST(N'1968-05-24T04:52:38.330' AS DateTime), CAST(N'1995-09-14T05:05:48.760' AS DateTime), N'', N'', 14, 1, N'img/online-learning-1.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 4)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (15, 1, 1, N'Combining Plot Styles', CAST(N'2002-11-14T02:29:32.900' AS DateTime), CAST(N'2004-04-16T03:45:01.040' AS DateTime), N'', N'', 15, 1, N'img/online-learning-2.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 4)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (16, 1, 2, N'Box and Violin Plots', CAST(N'1960-10-19T11:45:01.970' AS DateTime), CAST(N'1982-03-03T02:16:34.270' AS DateTime), N'', N'', 16, 1, NULL, CAST(N'2022-06-12T10:30:00.000' AS DateTime), 4)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (54, 2, 1, N'Installation Setup and Overview', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-23T00:00:00.000' AS DateTime), NULL, NULL, 1, 1, N'img/online-learning-1.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 5)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (55, 2, 1, N'IDEs and Course Resources', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 2, 1, N'img/online-learning-2.mp4', CAST(N'2022-06-12T10:30:00.000' AS DateTime), 5)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (56, 2, 1, N'iPython/Jupyter Notebook Overview', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 3, 1, N'img/online-learning-3.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 5)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (57, 2, 2, N'Course FAQs', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 4, 1, N'', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 5)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (58, 2, 1, N'Creating arrays', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 5, 1, N'img/online-learning-4.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 6)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (61, 2, 1, N'Using arrays and scalars', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 6, 1, N'img/online-learning-5.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 6)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (62, 2, 1, N'Indexing Arrays', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 7, 1, N'img/online-learning-6.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 6)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (63, 2, 2, N'Array Transposition', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 8, 1, N'', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 6)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (64, 2, 1, N'DataFrames', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 9, 1, N'img/online-learning-7.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 7)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (65, 2, 1, N'Index objects', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 10, 1, N'img/online-learning-8.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 7)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (66, 2, 1, N'Selecting Entries', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 11, 1, N'img/online-learning-9.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 7)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (67, 2, 2, N'Summary Statistics', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 12, 1, N'', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 7)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (68, 3, 1, N'Linear Regression Part 1', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 1, 1, N'img/online-learning-9.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 8)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (69, 3, 1, N'Linear Regression Part 2', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 2, 1, N'img/online-learning-9.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 8)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (70, 3, 1, N'Linear Regression Part 3', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 3, 1, N'img/online-learning-9.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 8)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (71, 3, 2, N'Linear Regression Part 4', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 4, 1, N'', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 8)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (72, 3, 1, N'Data Projects Preview', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 5, 1, N'img/online-learning-9.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 9)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (73, 3, 1, N'Intro to Data Projects', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 6, 1, N'img/online-learning-9.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 9)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (74, 3, 1, N'Titanic Project - Part 1', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 7, 1, N'img/online-learning-9.mp4', CAST(N'2022-06-30T10:30:00.000' AS DateTime), 9)
INSERT [dbo].[Lesson] ([LessonID], [CourseID], [LessonTypeID], [Name], [CreatedTime], [UpdatedTime], [WideImageUrl], [TinyImageUrl], [Order], [Status], [VideoUrl], [StartLearningTime], [SubLessonID]) VALUES (75, 3, 2, N'Titanic Project - Part 2', CAST(N'2022-12-12T00:00:00.000' AS DateTime), CAST(N'2022-12-12T00:00:00.000' AS DateTime), NULL, NULL, 8, 1, N'', NULL, 9)
SET IDENTITY_INSERT [dbo].[Lesson] OFF
GO
SET IDENTITY_INSERT [dbo].[Note] ON 

INSERT [dbo].[Note] ([NoteID], [LessonID], [NoteDescription], [CreatedTime], [NoteTimeInVideo], [AccountID]) VALUES (78, 3, N'huahaha', CAST(N'2022-06-11T11:21:52.843' AS DateTime), N'00:09', 12)
INSERT [dbo].[Note] ([NoteID], [LessonID], [NoteDescription], [CreatedTime], [NoteTimeInVideo], [AccountID]) VALUES (79, 3, N'dadadada', CAST(N'2022-06-11T11:22:07.473' AS DateTime), N'00:21', 12)
INSERT [dbo].[Note] ([NoteID], [LessonID], [NoteDescription], [CreatedTime], [NoteTimeInVideo], [AccountID]) VALUES (80, 15, N'what is e-learningddd', CAST(N'2022-06-11T12:11:32.293' AS DateTime), N'00:02', 12)
INSERT [dbo].[Note] ([NoteID], [LessonID], [NoteDescription], [CreatedTime], [NoteTimeInVideo], [AccountID]) VALUES (81, 15, N'electronic learning', CAST(N'2022-06-11T12:11:56.243' AS DateTime), N'00:09', 12)
INSERT [dbo].[Note] ([NoteID], [LessonID], [NoteDescription], [CreatedTime], [NoteTimeInVideo], [AccountID]) VALUES (83, 6, N'465789', CAST(N'2022-06-11T21:24:26.527' AS DateTime), N'00:01', 12)
INSERT [dbo].[Note] ([NoteID], [LessonID], [NoteDescription], [CreatedTime], [NoteTimeInVideo], [AccountID]) VALUES (84, 15, N'new note', CAST(N'2022-06-13T00:13:22.603' AS DateTime), N'00:15', 12)
INSERT [dbo].[Note] ([NoteID], [LessonID], [NoteDescription], [CreatedTime], [NoteTimeInVideo], [AccountID]) VALUES (87, 55, N'tdtrft', CAST(N'2022-06-15T10:32:32.607' AS DateTime), N'00:01', 12)
SET IDENTITY_INSERT [dbo].[Note] OFF
GO
SET IDENTITY_INSERT [dbo].[TransactionHistory] ON 

INSERT [dbo].[TransactionHistory] ([TransactionHistoryID], [AccountID], [CourseID], [Amount], [TrasactionTime]) VALUES (1, 1, 1, CAST(67.00 AS Decimal(15, 2)), CAST(N'2021-12-05T06:17:53.620' AS DateTime))
INSERT [dbo].[TransactionHistory] ([TransactionHistoryID], [AccountID], [CourseID], [Amount], [TrasactionTime]) VALUES (2, 3, 9, CAST(33.00 AS Decimal(15, 2)), CAST(N'2021-09-01T16:06:54.760' AS DateTime))
INSERT [dbo].[TransactionHistory] ([TransactionHistoryID], [AccountID], [CourseID], [Amount], [TrasactionTime]) VALUES (3, 6, 5, CAST(55.00 AS Decimal(15, 2)), CAST(N'2021-03-20T01:17:03.020' AS DateTime))
INSERT [dbo].[TransactionHistory] ([TransactionHistoryID], [AccountID], [CourseID], [Amount], [TrasactionTime]) VALUES (4, 11, 7, CAST(87.00 AS Decimal(15, 2)), CAST(N'2021-12-11T12:53:57.320' AS DateTime))
SET IDENTITY_INSERT [dbo].[TransactionHistory] OFF
GO



SET IDENTITY_INSERT [dbo].[QuestionLevel] ON 

INSERT [dbo].[QuestionLevel] ([QuestionLevelID], [LevelName], [Order], [Status], [type]) VALUES (1, N'Very Hard', 1, 1, N'LEVEL_QUESTION')
INSERT [dbo].[QuestionLevel] ([QuestionLevelID], [LevelName], [Order], [Status], [type]) VALUES (2, N'Medium', 2, 1, N'LEVEL_QUESTION')
INSERT [dbo].[QuestionLevel] ([QuestionLevelID], [LevelName], [Order], [Status], [type]) VALUES (3, N'Hard', 3, 1, N'LEVEL_QUESTION')
INSERT [dbo].[QuestionLevel] ([QuestionLevelID], [LevelName], [Order], [Status], [type]) VALUES (4, N'Easy', 4, 1, N'LEVEL_QUESTION')
INSERT [dbo].[QuestionLevel] ([QuestionLevelID], [LevelName], [Order], [Status], [type]) VALUES (6, N'fghj', 4, 1, N'LEVEL_QUESTION')
SET IDENTITY_INSERT [dbo].[QuestionLevel] OFF
GO



INSERT [dbo].[CompletedQuestion] ([AccountID], [QuestionID], [SelectedAnswerID], [Status]) VALUES (12, 109, 440, 0)
INSERT [dbo].[CompletedQuestion] ([AccountID], [QuestionID], [SelectedAnswerID], [Status]) VALUES (12, 109, 441, 1)
GO
INSERT [dbo].[CourseAccount] ([AccountID], [CourseID], [EnrollDate], [Rating], [Progress]) VALUES (12, 1, CAST(N'2021-12-05' AS Date), 5, 100)
INSERT [dbo].[CourseAccount] ([AccountID], [CourseID], [EnrollDate], [Rating], [Progress]) VALUES (12, 2, CAST(N'2021-12-05' AS Date), 5, 0)
INSERT [dbo].[CourseAccount] ([AccountID], [CourseID], [EnrollDate], [Rating], [Progress]) VALUES (12, 3, CAST(N'2021-09-01' AS Date), 3, 0)
GO
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 1, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 2, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 3, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 4, 0, 0, CAST(N'2022-06-17T14:12:21.607' AS DateTime), CAST(N'2022-06-17T14:12:27.437' AS DateTime))
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 5, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 6, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 7, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 9, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 10, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 11, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 13, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 14, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 15, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 16, 0, 0, CAST(N'2022-06-16T17:58:50.683' AS DateTime), NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 54, 10, 1, NULL, NULL)
INSERT [dbo].[CompletedLesson] ([AccountID], [LessonID], [Score], [Status], [StartTime], [EndTime]) VALUES (12, 55, 10, 1, NULL, NULL)
GO
SET IDENTITY_INSERT [dbo].[SubjectMainCategory] ON 

INSERT [dbo].[SubjectMainCategory] ([MainCategoryID], [Name]) VALUES (1, N'Calculation and analysis')
INSERT [dbo].[SubjectMainCategory] ([MainCategoryID], [Name]) VALUES (2, N'Research and development')
INSERT [dbo].[SubjectMainCategory] ([MainCategoryID], [Name]) VALUES (3, N'Cultural and community')
SET IDENTITY_INSERT [dbo].[SubjectMainCategory] OFF
GO
SET IDENTITY_INSERT [dbo].[SubjectCategory] ON 

INSERT [dbo].[SubjectCategory] ([CategoryID], [Name], [MainCategoryID]) VALUES (1, N'Business', 1)
INSERT [dbo].[SubjectCategory] ([CategoryID], [Name], [MainCategoryID]) VALUES (2, N'Science', 2)
INSERT [dbo].[SubjectCategory] ([CategoryID], [Name], [MainCategoryID]) VALUES (3, N'Technology', 2)
INSERT [dbo].[SubjectCategory] ([CategoryID], [Name], [MainCategoryID]) VALUES (4, N'Language', 3)
INSERT [dbo].[SubjectCategory] ([CategoryID], [Name], [MainCategoryID]) VALUES (5, N'Mathematics', 1)
INSERT [dbo].[SubjectCategory] ([CategoryID], [Name], [MainCategoryID]) VALUES (6, N'Culture', 3)
SET IDENTITY_INSERT [dbo].[SubjectCategory] OFF
GO
SET IDENTITY_INSERT [dbo].[Subject] ON 

INSERT [dbo].[Subject] ([SubjectID], [Name], [CategoryID], [MainCategoryID], [Featured], [Status], [Image], [Description], [Order], [type], [OwnerID]) VALUES (1, N'Biology & Life Sciences', 2, 2, 1, 1, N'cell-microbiology_spotlight.jpg', N'Biology & Life Sciences', 1, N'CATEGORY_SUBJECT', 9)
INSERT [dbo].[Subject] ([SubjectID], [Name], [CategoryID], [MainCategoryID], [Featured], [Status], [Image], [Description], [Order], [type], [OwnerID]) VALUES (2, N'Training', 3, NULL, 1, 1, N'training-dao-tao-trang-bi-nang-cao-kien-thuc-cho-nhan-vien.jpg', N'Training', 2, N'CATEGORY_SUBJECT', 10)
INSERT [dbo].[Subject] ([SubjectID], [Name], [CategoryID], [MainCategoryID], [Featured], [Status], [Image], [Description], [Order], [type], [OwnerID]) VALUES (3, N'Language', 4, 3, 1, 1, N'10-language-trends-in-elearning.jpg', N'Language', 3, N'CATEGORY_SUBJECT', 9)
INSERT [dbo].[Subject] ([SubjectID], [Name], [CategoryID], [MainCategoryID], [Featured], [Status], [Image], [Description], [Order], [type], [OwnerID]) VALUES (4, N'Design', 3, NULL, 0, 1, N'design.jpg', N'Design', 4, N'CATEGORY_SUBJECT', 10)
INSERT [dbo].[Subject] ([SubjectID], [Name], [CategoryID], [MainCategoryID], [Featured], [Status], [Image], [Description], [Order], [type], [OwnerID]) VALUES (5, N'Data Analysis & Statistics', 5, 1, 0, 1, N'main-qimg-86093833f5568a3579edf89c97254a68.jpg', N'Data Analysis & Statistics', 5, N'CATEGORY_SUBJECT', 9)
INSERT [dbo].[Subject] ([SubjectID], [Name], [CategoryID], [MainCategoryID], [Featured], [Status], [Image], [Description], [Order], [type], [OwnerID]) VALUES (6, N'Architecture', 3, NULL, 0, 1, N'model-architecture.jpg', N'Architecture', 6, N'CATEGORY_SUBJECT', 10)
INSERT [dbo].[Subject] ([SubjectID], [Name], [CategoryID], [MainCategoryID], [Featured], [Status], [Image], [Description], [Order], [type], [OwnerID]) VALUES (7, N'Ethics', 5, NULL, 0, 1, N'Ethics-Blog-760x550-760x550.png', N'Ethics', 7, N'CATEGORY_SUBJECT', 9)
INSERT [dbo].[Subject] ([SubjectID], [Name], [CategoryID], [MainCategoryID], [Featured], [Status], [Image], [Description], [Order], [type], [OwnerID]) VALUES (8, N'Art & Culture', 6, NULL, 1, 1, N'Expeditions_Header_Artwork_Card_Size_1.max-1000x1000.jpg', N'Art & Culture', 8, N'CATEGORY_SUBJECT', 10)
INSERT [dbo].[Subject] ([SubjectID], [Name], [CategoryID], [MainCategoryID], [Featured], [Status], [Image], [Description], [Order], [type], [OwnerID]) VALUES (9, N'Chemistry', 2, NULL, 0, 1, N'chemistry_logo.png', N'Chemistry', 9, N'CATEGORY_SUBJECT', 9)
INSERT [dbo].[Subject] ([SubjectID], [Name], [CategoryID], [MainCategoryID], [Featured], [Status], [Image], [Description], [Order], [type], [OwnerID]) VALUES (10, N'Economics & Finance', 1, 1, 0, 1, N'activetrading1-5bfc2b9cc9e77c0026b4fbf9.jpg', N'Economics & Finance ', 10, N'CATEGORY_SUBJECT', 10)
SET IDENTITY_INSERT [dbo].[Subject] OFF
GO
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (9, 1)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (9, 3)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (9, 4)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (9, 5)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (9, 7)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (9, 8)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (9, 9)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (10, 1)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (10, 2)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (10, 3)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (10, 4)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (10, 6)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (10, 8)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (10, 9)
INSERT [dbo].[SubjectAccount] ([AccountID], [SubjectID]) VALUES (10, 10)
GO
SET IDENTITY_INSERT [dbo].[Slider] ON 

INSERT [dbo].[Slider] ([SliderID], [Title], [SubTitle], [Description], [NavigationLink], [ImageUrl], [Order], [Status], [SliderCollectionID]) VALUES (1, N'Software Development Lifecycle', N'Sponsored by FPT University', N'This Specialization is designed for people who are new to software engineering. It is also for those who have already developed software, but wish to gain a deeper understanding of the underlying context and theory of software development practices.', N'https://stackoverflow.com/questions/5718191/use-current-date-as-default-value-for-a-column', N'software-product-development-life-cycle_1.png', 1, 1, 1)
INSERT [dbo].[Slider] ([SliderID], [Title], [SubTitle], [Description], [NavigationLink], [ImageUrl], [Order], [Status], [SliderCollectionID]) VALUES (2, N'Web Design for Everybody: Basics of Web Development & Coding', N'Sponsored by FPT University', N'This Specialization covers how to write syntactically correct HTML5 and CSS3, and how to create interactive web experiences with JavaScript. Mastering this range of technologies will allow you to develop high quality web sites that, work seamlessly on mobile, tablet, and large screen browsers accessible.,', N'https://stackoverflow.com/questions/5718191/use-current-date-as-default-value-for-a-column', N'2uYd8j4.jpeg', 1, 1, 1)
INSERT [dbo].[Slider] ([SliderID], [Title], [SubTitle], [Description], [NavigationLink], [ImageUrl], [Order], [Status], [SliderCollectionID]) VALUES (3, N'Requirements Engineering: Secure Software Specifications', N'Sponsored by FPT University', N'This specialization is intended for software engineers, development and product managers, testers, QA analysts, product analysts, tech writers, and security engineers.', N'https://stackoverflow.com/questions/5718191/use-current-date-as-default-value-for-a-column', N'Blog-Know-how-to-secure-web-application-PE-e1531381499384.jpg', 1, 1, 1)
INSERT [dbo].[Slider] ([SliderID], [Title], [SubTitle], [Description], [NavigationLink], [ImageUrl], [Order], [Status], [SliderCollectionID]) VALUES (4, N'Project Management Principles and Practices', N'Sponsored by FPT University', N'This specialization is a precursor to the Applied Project Management Certificate. Project management has been proven to be the most effective method of delivering products within cost, schedule, and resource constraints.', N'https://stackoverflow.com/questions/5718191/use-current-date-as-default-value-for-a-column', N'PM.avif', 1, 1, 1)
INSERT [dbo].[Slider] ([SliderID], [Title], [SubTitle], [Description], [NavigationLink], [ImageUrl], [Order], [Status], [SliderCollectionID]) VALUES (9, N'xcvba', N'xcvb', N'xcvb', N'xcvb', N'cat-1.jpg', 1, 0, 2)
INSERT [dbo].[Slider] ([SliderID], [Title], [SubTitle], [Description], [NavigationLink], [ImageUrl], [Order], [Status], [SliderCollectionID]) VALUES (14, N'da thay doi da thay doi', N'da thay doi da thay doi', N'da thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doida thay doi da thay doi', N'da thay doi da thay doi', N'xiaomi-mi-gaming-laptop-stock-4k-nx-1920x1080.jpg', 1, 0, 2)
SET IDENTITY_INSERT [dbo].[Slider] OFF
GO
SET IDENTITY_INSERT [dbo].[DimensionType] ON 

INSERT [dbo].[DimensionType] ([TypeID], [Name]) VALUES (1, N'Domain')
INSERT [dbo].[DimensionType] ([TypeID], [Name]) VALUES (2, N'Group')
SET IDENTITY_INSERT [dbo].[DimensionType] OFF
GO

SET IDENTITY_INSERT [dbo].[QuizLesson] ON
INSERT [dbo].[QuizLesson] ([LessonID], [Note], [PassScore], [QuizTimeInMinute], [QuizID], [Name], [SubjectID], [Level], [TotalQuestion], [Type]) VALUES (1, N'plorum', 6, 1, 1, N'Microsoft Excel files with Pythons', 1, N'Medium', 0, N'Text')
INSERT [dbo].[QuizLesson] ([LessonID], [Note], [PassScore], [QuizTimeInMinute], [QuizID], [Name], [SubjectID], [Level], [TotalQuestion], [Type]) VALUES (2, N'volcans non', 5, 1, 2, N'HTML with Python', 2, N'Medium', NULL, N'Text')
INSERT [dbo].[QuizLesson] ([LessonID], [Note], [PassScore], [QuizTimeInMinute], [QuizID], [Name], [SubjectID], [Level], [TotalQuestion], [Type]) VALUES (3, N'estis quo, Sed', 5, 1, 3, N'Reading and Writing Text Files', 3, N'Medium', NULL, N'Text')
INSERT [dbo].[QuizLesson] ([LessonID], [Note], [PassScore], [QuizTimeInMinute], [QuizID], [Name], [SubjectID], [Level], [TotalQuestion], [Type]) VALUES (4, N'et venit. gravum', 4, 1, 4, N'JSON with Python', 4, N'Medium', 8, N'Text')
INSERT [dbo].[QuizLesson] ([LessonID], [Note], [PassScore], [QuizTimeInMinute], [QuizID], [Name], [SubjectID], [Level], [TotalQuestion], [Type]) VALUES (5, N'cognitio,', 2, 1, 5, N'Merge on Index', 5, N'Medium', NULL, N'Text')
INSERT [dbo].[QuizLesson] ([LessonID], [Note], [PassScore], [QuizTimeInMinute], [QuizID], [Name], [SubjectID], [Level], [TotalQuestion], [Type]) VALUES (6, N'fecundio,', 10, 1, 6, N'Combining DataFrames', 6, N'Easy', NULL, N'Video')
INSERT [dbo].[QuizLesson] ([LessonID], [Note], [PassScore], [QuizTimeInMinute], [QuizID], [Name], [SubjectID], [Level], [TotalQuestion], [Type]) VALUES (7, N'e fecit, pars', 8, 1, 7, N'Duplicates in DataFrames', 7, N'Easy', NULL, N'Video')
INSERT [dbo].[QuizLesson] ([LessonID], [Note], [PassScore], [QuizTimeInMinute], [QuizID], [Name], [SubjectID], [Level], [TotalQuestion], [Type]) VALUES (8, N'Quad habitatio', 0, 1, 8, N'Rename Index', 8, N'Easy', NULL, N'Video')
INSERT [dbo].[QuizLesson] ([LessonID], [Note], [PassScore], [QuizTimeInMinute], [QuizID], [Name], [SubjectID], [Level], [TotalQuestion], [Type]) VALUES (9, N'ut egreddior non', 4, 1, 9, N'GroupBy on DataFrames', 9, N'Easy', NULL, N'Video')
INSERT [dbo].[QuizLesson] ([LessonID], [Note], [PassScore], [QuizTimeInMinute], [QuizID], [Name], [SubjectID], [Level], [TotalQuestion], [Type]) VALUES (10, N'vobis quad', 8, 1, 10, N'GroupBy on Dict and Series', 10, N'Hard', NULL, N'Video')
SET IDENTITY_INSERT [dbo].[QuizLesson] OFF
GO

SET IDENTITY_INSERT [dbo].[Question] ON 

INSERT [dbo].[Question] ([QuestionID], [QuestionText], [QuestionImageUrl], [LessonID], [QuestionLevelID], [Order], [Status]) VALUES (109, N'The collection and summarization of the socioeconomic and physical characteristics of the employees of a particular firm is an example of', NULL, 4, 1, 1, 1)
INSERT [dbo].[Question] ([QuestionID], [QuestionText], [QuestionImageUrl], [LessonID], [QuestionLevelID], [Order], [Status]) VALUES (110, N'The outcome of an experiment is the number of resulting heads when a nickel and a dime are flipped simultaneously. What is the sample space for this experiment?', NULL, 4, 1, 2, 1)
INSERT [dbo].[Question] ([QuestionID], [QuestionText], [QuestionImageUrl], [LessonID], [QuestionLevelID], [Order], [Status]) VALUES (111, N'Suppose that on a particular multiple choice question, 96% of the students answered correctly. What is the probability that a randomly selected student answered the question incorrectly?', NULL, 4, 2, 3, 1)
INSERT [dbo].[Question] ([QuestionID], [QuestionText], [QuestionImageUrl], [LessonID], [QuestionLevelID], [Order], [Status]) VALUES (112, N'If you flip a coin three times, the possible outcomes are HHH HHT HTH HTT THH THT TTH TTT. What is the probability of getting at most one head?', NULL, 4, 2, 4, 1)
INSERT [dbo].[Question] ([QuestionID], [QuestionText], [QuestionImageUrl], [LessonID], [QuestionLevelID], [Order], [Status]) VALUES (114, N'Assume that P(C) = 0.5 and P(D) = 0.3. If C and D are independent, find P(C and D).', NULL, 4, 1, 6, 1)
INSERT [dbo].[Question] ([QuestionID], [QuestionText], [QuestionImageUrl], [LessonID], [QuestionLevelID], [Order], [Status]) VALUES (116, N'The probability that an individual is left-handed is 0.15. In a class of 30 students, what is the probability of finding five left-handers?', NULL, 4, 2, 8, 1)
INSERT [dbo].[Question] ([QuestionID], [QuestionText], [QuestionImageUrl], [LessonID], [QuestionLevelID], [Order], [Status]) VALUES (117, N'TProduct codes of 3, 4 or 5 letters are equally likely. What is the mean of the number of letters in 20 codes?', NULL, 4, 4, 9, 1)
INSERT [dbo].[Question] ([QuestionID], [QuestionText], [QuestionImageUrl], [LessonID], [QuestionLevelID], [Order], [Status]) VALUES (118, N'Find the mean for the binomial distribution which has the stated values of n = 20 and p = 3/5. Round answer to the nearest tenth.', NULL, 4, 4, 10, 1)
SET IDENTITY_INSERT [dbo].[Question] OFF
GO

SET IDENTITY_INSERT [dbo].[Dimension] ON 

INSERT [dbo].[Dimension] ([DimensionID], [Name], [Description], [TypeID], [SubjectID], [QuestionID], [LessonID]) VALUES (1, N'Business', N'Business', 1, 10, 109, 4)
INSERT [dbo].[Dimension] ([DimensionID], [Name], [Description], [TypeID], [SubjectID], [QuestionID], [LessonID]) VALUES (2, N'Process', N'Process', 1, 9, 110, 4)
INSERT [dbo].[Dimension] ([DimensionID], [Name], [Description], [TypeID], [SubjectID], [QuestionID], [LessonID]) VALUES (3, N'People', N'People', 1, 8, 111, 4)
INSERT [dbo].[Dimension] ([DimensionID], [Name], [Description], [TypeID], [SubjectID], [QuestionID], [LessonID]) VALUES (5, N'Planning', N'Planning', 2, 9, 112, 4)
INSERT [dbo].[Dimension] ([DimensionID], [Name], [Description], [TypeID], [SubjectID], [QuestionID], [LessonID]) VALUES (6, N'Executing', N'Executing', 2, 8, 114, 4)
INSERT [dbo].[Dimension] ([DimensionID], [Name], [Description], [TypeID], [SubjectID], [QuestionID], [LessonID]) VALUES (39, N'Initiating', N'Initiating', 2, 10, 116, 4)
SET IDENTITY_INSERT [dbo].[Dimension] OFF
GO
SET IDENTITY_INSERT [dbo].[BlogCategory] ON 

INSERT [dbo].[BlogCategory] ([BlogCategoryID], [Name], [Description], [IconUrl], [ThumbnailUrl], [Order], [Status], [type]) VALUES (1, N'Computer Science', N'Computer Science is the study of computers and computational systems', N'https://www.edx.org/images/logos/edx-logo-elm.svg', N'https://wallpapercave.com/wp/wp2972537.jpg', 1, 1, N'CATEGORY_POST')
INSERT [dbo].[BlogCategory] ([BlogCategoryID], [Name], [Description], [IconUrl], [ThumbnailUrl], [Order], [Status], [type]) VALUES (2, N'Information Technology', N'fecit. Pro quad', N'https://www.edx.org/images/logos/edx-logo-elm.svg', N'https://wallpapercave.com/wp/HKR4YOa.jpg', 2, 1, N'CATEGORY_POST')
INSERT [dbo].[BlogCategory] ([BlogCategoryID], [Name], [Description], [IconUrl], [ThumbnailUrl], [Order], [Status], [type]) VALUES (3, N'Math and Logic', N'regit,', N'https://www.edx.org/images/logos/edx-logo-elm.svg', N'https://wallpapercave.com/wp/wp2790127.jpg', 3, 1, N'CATEGORY_POST')
INSERT [dbo].[BlogCategory] ([BlogCategoryID], [Name], [Description], [IconUrl], [ThumbnailUrl], [Order], [Status], [type]) VALUES (4, N'Arts and Humanities', N'Id et quo quartu', N'https://nationalhumanitiescenter.org/wp-content/uploads/2019/04/nhc-hmaws-laptop-1440x900.jpg', N'https://nationalhumanitiescenter.org/wp-content/uploads/2019/04/nhc-hmaws-laptop-1440x900.jpg', 4, 1, N'CATEGORY_POST')
INSERT [dbo].[BlogCategory] ([BlogCategoryID], [Name], [Description], [IconUrl], [ThumbnailUrl], [Order], [Status], [type]) VALUES (5, N'Business', N'linguens plorum', N'https://nationalhumanitiescenter.org/wp-content/uploads/2019/04/nhc-hmaws-laptop-1440x900.jpg', N'https://wallpaperaccess.com/full/656670.jpg', 5, 1, N'CATEGORY_POST')
SET IDENTITY_INSERT [dbo].[BlogCategory] OFF
SET IDENTITY_INSERT [dbo].[BlogSubCategory] ON 

INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (1, N'IT Certifications', 1)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (2, N'Network & Security', 1)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (3, N'Hardware', 1)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (4, N'Operating Systems & Servers', 1)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (5, N'DevOps', 2)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (6, N'Python', 2)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (7, N'Docker', 2)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (8, N'Caculus', 3)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (9, N'Statistic', 3)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (10, N'Linear Algebra', 3)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (11, N'Phographer', 4)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (12, N'Design', 4)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (13, N'Digital Marketing', 5)
INSERT [dbo].[BlogSubCategory] ([BlogSubCategoryID], [SubCategoryName], [BlogCategoryID]) VALUES (14, N'Social Media Marketing', 5)
SET IDENTITY_INSERT [dbo].[BlogSubCategory] OFF
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (1, 1)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (1, 2)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (2, 4)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (3, 5)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (4, 1)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (5, 4)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (6, 1)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (8, 3)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (8, 4)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (11, 2)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (14, 1)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (15, 4)
INSERT [dbo].[BlogCategoryBlog] ([BlogID], [BlogCategoryID]) VALUES (16, 5)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (1, 4)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (1, 6)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (1, 14)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (2, 4)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (2, 6)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (2, 14)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (3, 4)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (5, 1)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (5, 11)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (6, 1)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (6, 11)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (7, 11)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (8, 8)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (9, 8)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (10, 8)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (11, 2)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (11, 5)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (11, 8)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (12, 5)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (12, 8)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (12, 15)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (13, 3)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (13, 16)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (14, 3)
INSERT [dbo].[BlogSubCategoryBlog] ([BlogSubCategoryID], [BlogID]) VALUES (14, 16)
GO

INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (1, 1)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (1, 2)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (1, 3)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (1, 4)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (1, 5)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (2, 6)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (2, 7)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (2, 8)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (2, 9)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (3, 1)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (3, 2)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (3, 5)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (4, 1)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (4, 2)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (4, 6)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (4, 7)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (5, 7)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (5, 8)
INSERT [dbo].[SubjectCourse] ([SubjectID], [CourseID]) VALUES (5, 9)

GO


SET IDENTITY_INSERT [dbo].[Answer] ON 

INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (440, N'a. a statistic.', N'The correct answer is: descriptive statistics.', 0, 109)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (441, N'b. a parameter.', N'The correct answer is: descriptive statistics.', 1, 109)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (442, N'c. descriptive statistics.', N'The correct answer is: descriptive statistics.', 1, 109)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (443, N'd. inferential statistics.', N'The correct answer is: descriptive statistics.', 0, 109)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (444, N'a. {HH, HT, TH, TT}', N'The correct answer is: {0, 1, 2}', 0, 110)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (445, N'b. {nickel, dime}', N'The correct answer is: {0, 1, 2}', 0, 110)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (446, N'c. {HH, HT, TT}', N'The correct answer is: {0, 1, 2}', 0, 110)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (447, N'd. {0, 1, 2}', N'The correct answer is: {0, 1, 2}', 1, 110)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (448, N'a. 0.14', N'The correct answer is: 0.04', 0, 111)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (449, N'b. 0.48', N'The correct answer is: 0.04', 0, 111)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (450, N'c. 0.96', N'The correct answer is: 0.04', 0, 111)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (451, N'd. 0.04', N'The correct answer is: 0.04', 1, 111)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (452, N'a. 6/7', N'The correct answer is: 1/2', 0, 112)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (453, N'b. 7/8', N'The correct answer is: 1/2', 0, 112)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (454, N'c. 5/6', N'The correct answer is: 1/2', 0, 112)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (455, N'd. 1/2', N'The correct answer is: 1/2', 1, 112)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (456, N'a. 0.5', N'The correct answer is: 0.15', 0, 114)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (457, N'b. 1.5', N'The correct answer is: 0.15', 0, 114)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (458, N'c. 0.15', N'The correct answer is: 0.15', 1, 114)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (459, N'd. 0.3', N'The correct answer is: 0.15', 0, 114)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (460, N'a. 0.153', N'The correct answer is: 0.186', 0, 116)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (461, N'b. 0.054', N'The correct answer is: 0.186', 0, 116)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (462, N'c. 0.186', N'The correct answer is: 0.186', 1, 116)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (463, N'd. 0.002', N'The correct answer is: 0.186', 0, 116)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (464, N'a. 40', N'The correct answer is: 80', 0, 117)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (465, N'b. 4', N'The correct answer is: 80', 0, 117)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (466, N'c. 8', N'The correct answer is: 80', 0, 117)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (467, N'd. 80', N'The correct answer is: 80', 1, 117)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (472, N'a. 12.0', N'The correct answer is: 12.0', 1, 118)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (473, N'b. 12.7', N'The correct answer is: 12.0', 0, 118)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (474, N'c. 12.3', N'The correct answer is: 12.0', 0, 118)
INSERT [dbo].[Answer] ([AnswerID], [AnswerText], [Explain], [Status], [QuestionID]) VALUES (475, N'd. 11.5', N'The correct answer is: 12.0', 0, 118)
SET IDENTITY_INSERT [dbo].[Answer] OFF
GO
SET IDENTITY_INSERT [dbo].[PricePackage] ON 

INSERT [dbo].[PricePackage] ([PriceID], [Name], [AccessDuration], [Status], [ListPrice], [SalePrice], [SubjectID]) VALUES (1, N'Gói truy cập 3 tháng', 3, 1, CAST(3600.00 AS Decimal(15, 2)), CAST(3200.00 AS Decimal(15, 2)), 10)
INSERT [dbo].[PricePackage] ([PriceID], [Name], [AccessDuration], [Status], [ListPrice], [SalePrice], [SubjectID]) VALUES (2, N'Gói truy cập 6 tháng', 6, 0, CAST(5000.00 AS Decimal(15, 2)), CAST(4500.00 AS Decimal(15, 2)), 10)
INSERT [dbo].[PricePackage] ([PriceID], [Name], [AccessDuration], [Status], [ListPrice], [SalePrice], [SubjectID]) VALUES (3, N'Gói truy cập vô thời hạn', NULL, 1, CAST(10000.00 AS Decimal(15, 2)), CAST(9800.00 AS Decimal(15, 2)), 10)
INSERT [dbo].[PricePackage] ([PriceID], [Name], [AccessDuration], [Status], [ListPrice], [SalePrice], [SubjectID]) VALUES (7, N'sdfsdf', 34, 1, CAST(23.00 AS Decimal(15, 2)), CAST(0.00 AS Decimal(15, 2)), 10)
SET IDENTITY_INSERT [dbo].[PricePackage] OFF
GO
SET IDENTITY_INSERT [dbo].[QuizSession] ON 

INSERT [dbo].[QuizSession] ([SessionID], [AccountID], [QuizLessonID], [StartTime], [ExpiredTime]) VALUES (1, 12, 4, CAST(N'2022-06-17T14:12:21.607' AS DateTime), CAST(N'2022-06-17T14:13:21.607' AS DateTime))
INSERT [dbo].[QuizSession] ([SessionID], [AccountID], [QuizLessonID], [StartTime], [ExpiredTime]) VALUES (2, 12, 16, CAST(N'2022-06-16T17:58:50.683' AS DateTime), CAST(N'2022-06-16T18:08:50.683' AS DateTime))
SET IDENTITY_INSERT [dbo].[QuizSession] OFF
GO
SET IDENTITY_INSERT [dbo].[Setting] ON 

INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (1, 1, N'EXPERT2', 2, 1, N'USER_ROLE')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (2, 2, N'CUSTOMERs', 5, 1, N'USER_ROLE')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (3, 3, N'SALE', 4, 1, N'USER_ROLE')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (4, 4, N'ADMIN', 1, 1, N'USER_ROLE')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (5, 5, N'MARKETING', 3, 1, N'USER_ROLE')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (6, 1, N'Biology & Life Sciences', 1, 1, N'CATEGORY_SUBJECT')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (7, 2, N'Training', 2, 1, N'CATEGORY_SUBJECT')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (8, 3, N'Languages', 3, 0, N'CATEGORY_SUBJECT')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (9, 4, N'Design', 4, 0, N'CATEGORY_SUBJECT')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (10, 5, N'Data Analysis & Statistics', 5, 1, N'CATEGORY_SUBJECT')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (11, 6, N'Architecture', 6, 1, N'CATEGORY_SUBJECT')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (12, 7, N'Ethics', 7, 1, N'CATEGORY_SUBJECT')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (13, 8, N'Art & Culture', 8, 1, N'CATEGORY_SUBJECT')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (14, 9, N'Chemistry', 9, 1, N'CATEGORY_SUBJECT')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (15, 10, N'Economics & Finance', 10, 1, N'CATEGORY_SUBJECT')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (16, 1, N'Very Hard', 1, 1, N'LEVEL_QUESTION')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (17, 2, N'Medium', 2, 1, N'LEVEL_QUESTION')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (18, 3, N'Hard', 3, 1, N'LEVEL_QUESTION')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (19, 4, N'Easy', 4, 1, N'LEVEL_QUESTION')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (20, 1, N'Video Lesson ', 2, 1, N'TYPE_LESSON')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (21, 2, N' Quiz Lesson', 1, 1, N'TYPE_LESSON')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (22, 1, N'Game', 1, 1, N'CATEGORY_POST')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (23, 2, N'Music', 2, 1, N'CATEGORY_POST')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (24, 3, N'Education', 3, 1, N'CATEGORY_POST')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (25, 4, N'Music', 4, 1, N'CATEGORY_POST')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (26, 5, N'Computer', 5, 1, N'CATEGORY_POST')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (29, 6, N'Languages', 4, 1, N'USER_ROLE')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (38, 22, N'MANAGER', 7, 0, N'USER_ROLE')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (40, 5, N'Really Hard', 7, 0, N'LEVEL_QUESTION')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (41, 3, N'Text', 11, 0, N'TYPE_LESSON')
INSERT [dbo].[Setting] ([SettingID], [id], [Name], [Order], [Status], [type]) VALUES (50, 5, N'fghj', 4, 1, N'LEVEL_QUESTION')
SET IDENTITY_INSERT [dbo].[Setting] OFF
GO
SET IDENTITY_INSERT [dbo].[Objective] ON 

INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (1, N'Have an intermediate skill level of Python programming.', 1)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (2, N'Use the Jupyter Notebook Environment.
', 1)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (3, N'Use the numpy library to create and manipulate arrays.
', 1)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (4, N'Have a portfolio of various data analysis projects.
', 1)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (5, N'Use the pandas module with Python to create and structure data.
', 1)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (6, N'Have an intermediate skill level of Python programming.', 2)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (7, N'Have an intermediate skill level of Python programming.', 3)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (8, N'Have an intermediate skill level of Python programming.', 4)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (9, N'Have an intermediate skill level of Python programming.', 5)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (10, N'Have an intermediate skill level of Python programming.', 6)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (11, N'Have an intermediate skill level of Python programming.', 7)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (12, N'Have an intermediate skill level of Python programming.', 8)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (13, N'Have an intermediate skill level of Python programming.', 9)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (14, N'Use the Jupyter Notebook Environment.
', 2)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (15, N'Use the Jupyter Notebook Environment.
', 3)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (16, N'Use the Jupyter Notebook Environment.
', 4)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (17, N'Use the Jupyter Notebook Environment.
', 5)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (18, N'Use the Jupyter Notebook Environment.
', 6)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (19, N'Use the Jupyter Notebook Environment.
', 7)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (20, N'Use the Jupyter Notebook Environment.
', 8)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (21, N'Use the Jupyter Notebook Environment.
', 9)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (22, N'Use the numpy library to create and manipulate arrays.
', 2)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (23, N'Use the numpy library to create and manipulate arrays.
', 3)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (24, N'Use the numpy library to create and manipulate arrays.
', 4)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (25, N'Use the numpy library to create and manipulate arrays.
', 5)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (26, N'Use the numpy library to create and manipulate arrays.
', 6)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (27, N'Use the numpy library to create and manipulate arrays.
', 7)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (28, N'Use the numpy library to create and manipulate arrays.
', 8)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (29, N'Use the numpy library to create and manipulate arrays.
', 9)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (30, N'Have a portfolio of various data analysis projects.
', 2)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (31, N'Have a portfolio of various data analysis projects.
', 3)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (32, N'Have a portfolio of various data analysis projects.
', 4)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (33, N'Have a portfolio of various data analysis projects.
', 5)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (34, N'Have a portfolio of various data analysis projects.
', 6)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (35, N'Have a portfolio of various data analysis projects.
', 7)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (36, N'Have a portfolio of various data analysis projects.
', 8)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (37, N'Have a portfolio of various data analysis projects.
', 9)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (38, N'Use the pandas module with Python to create and structure data.
', 2)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (39, N'Use the pandas module with Python to create and structure data.
', 3)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (40, N'Use the pandas module with Python to create and structure data.
', 4)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (41, N'Use the pandas module with Python to create and structure data.
', 5)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (42, N'Use the pandas module with Python to create and structure data.
', 6)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (43, N'Use the pandas module with Python to create and structure data.
', 7)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (44, N'Use the pandas module with Python to create and structure data.
', 8)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (45, N'Use the pandas module with Python to create and structure data.
', 9)
INSERT [dbo].[Objective] ([ObjectiveID], [ObjectiveName], [CourseID]) VALUES (46, N'Use the pandas module with Python to create and structure data.
', NULL)
SET IDENTITY_INSERT [dbo].[Objective] OFF
